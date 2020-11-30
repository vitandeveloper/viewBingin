package com.example.marlonviana.adapter

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.marlonviana.AppMaster
import com.example.marlonviana.R
import com.example.marlonviana.event.EventTapCharacter
import com.example.marlonviana.model.DataCharacters
import com.example.marlonviana.util.setAnimationPushButton

class AdapterCharacter: RecyclerView.Adapter<AdapterCharacter.ViewHolder>() {
    private var items: MutableList<DataCharacters>  = ArrayList()
    private lateinit var eventTapCharacter: EventTapCharacter

    fun adapterConstructor(items : MutableList<DataCharacters>, eventTap: EventTapCharacter){
        this.items = items
        this.eventTapCharacter = eventTap
    }

    class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val textComic = view.findViewById(R.id.textComic) as TextView
        val textComicDescrip = view.findViewById(R.id.textComicDescrip) as TextView
        val imageComic = view.findViewById(R.id.imageComic) as ImageView
    }

    @SuppressLint("NewApi", "SetTextI18n")
    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.textComic.text = items[position].name
        holder.textComicDescrip.text = items[position].description

        Glide.with(AppMaster.contextApp())
            .load(items[position].thumbnail?.path+"."+items[position].thumbnail?.extension)
            .into(holder.imageComic)

        holder.itemView.setOnClickListener {
            holder.itemView.setAnimationPushButton()
            eventTapCharacter.tapCharacter(items[position])
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        return ViewHolder(layoutInflater.inflate(R.layout.item_character, parent, false))
    }

    override fun getItemCount(): Int {
        return items.size
    }

    override fun getItemViewType(position: Int): Int {
        return position
    }
}