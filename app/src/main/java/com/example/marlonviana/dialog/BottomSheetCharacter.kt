package com.example.marlonviana.dialog

import android.annotation.SuppressLint
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import com.areuin.android.room.AppDataBase
import com.bumptech.glide.Glide
import com.example.marlonviana.AppMaster
import com.example.marlonviana.R
import com.example.marlonviana.adapter.AdapterSeries
import com.example.marlonviana.event.EventUpdateFav
import com.example.marlonviana.model.DataCharacters
import com.example.marlonviana.util.setAnimationPushButton
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import kotlinx.android.synthetic.main.bottomshet_character.*
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch

class BottomSheetCharacter: BottomSheetDialogFragment() {
    private lateinit var data: DataCharacters
    private lateinit var eventUpdateFave: EventUpdateFav
    private val dao = AppDataBase.getInstance().daoRoom()

    companion object{
        fun initData(d: DataCharacters,event: EventUpdateFav) = BottomSheetCharacter().apply {
            arguments = Bundle().apply {
                data = d
                eventUpdateFave = event
            }
        }
    }

    override fun onStart() {
        super.onStart()
        dialog!!.window!!.decorView.setBackgroundResource(R.color.black_traslucent)
    }

    @SuppressLint("InflateParams")
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return activity!!.layoutInflater.inflate(R.layout.bottomshet_character, null)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        buttonFav.setOnClickListener {
            it.setAnimationPushButton()
            processFav()
        }

        isAddFav()
        initRecyclers()
        processData()
    }

    private fun initRecyclers(){
        val llmVTP = LinearLayoutManager(context)
        llmVTP.orientation = LinearLayoutManager.VERTICAL
        recyclerComics.layoutManager = llmVTP
        recyclerComics.setHasFixedSize(true)

        val llmVTPP = LinearLayoutManager(context)
        llmVTPP.orientation = LinearLayoutManager.VERTICAL
        recyclerSeries.layoutManager = llmVTPP
        recyclerSeries.setHasFixedSize(true)
    }

    private fun processData(){
        Glide.with(AppMaster.contextApp())
            .load(data.thumbnail?.path+"."+data.thumbnail?.extension)
            .into(imageComic)

        textComic.text = data.name
        textComicDescrip.text = data.description

        if (data.comics?.items.isNullOrEmpty()){
            textViewComics.visibility = View.GONE
            recyclerComics.visibility = View.GONE
        }
        else
        {
            val adapterC = AdapterSeries()
            adapterC.adapterConstructor(data.comics!!.items!!)
            recyclerComics.adapter = adapterC
        }

        if (data.series?.items.isNullOrEmpty())
        {
            textViewSeries.visibility = View.GONE
            recyclerSeries.visibility = View.GONE
        }
        else
        {
            val adapterS = AdapterSeries()
            adapterS.adapterConstructor(data.series!!.items!!)
            recyclerSeries.adapter = adapterS
        }
    }

    private fun isAddFav(){
        GlobalScope.launch {
            if(dao.getCharacters(data.id!!)!=null)
            {
                isFav(true)
            }
        }
    }

    private fun processFav(){
        GlobalScope.launch {
            if(dao.getCharacters(data.id!!)==null)
            {
                dao.insterCharcter(data)
                isFav(true)
            }
            else
            {
                dao.deletCharacterr(data.id!!)
                isFav(false)
            }
            eventUpdateFave.upDate()
            dismiss()
        }
    }

    private fun isFav(fav:Boolean) {
        if (fav)
        {
            buttonFav.setImageResource(R.drawable.ic_fav_solid)
        }
        else
        {
            buttonFav.setImageResource(R.drawable.ic_fav_line)
        }
    }
}