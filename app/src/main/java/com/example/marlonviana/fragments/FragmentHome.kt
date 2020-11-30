package com.example.marlonviana.fragments

import android.os.Bundle
import android.view.View
import androidx.core.widget.NestedScrollView
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.GridLayoutManager
import com.areuin.android.api.BodyApiManager
import com.example.marlonviana.R
import com.example.marlonviana.adapter.AdapterCharacter
import com.example.marlonviana.adapter.DecoratorGridRecycler
import com.example.marlonviana.databinding.FragmentHomeBinding
import com.example.marlonviana.dialog.BottomSheetCharacter
import com.example.marlonviana.event.EventTapCharacter
import com.example.marlonviana.event.EventUpdateFav
import com.example.marlonviana.model.DataCharacters
import com.example.marlonviana.model.RespondCharacter
import com.example.marlonviana.util.*
import com.example.marlonviana.viewmodel.HomeViewModel

class FragmentHome : Fragment(R.layout.fragment_home) {
    private var _binding: FragmentHomeBinding? = null
    private val binding get() = _binding!!
    private var listCharacter: MutableList<DataCharacters>  = ArrayList()
    private val adapter =  AdapterCharacter()
    private val viewModel by lazy {
        ViewModelProvider(this).get(HomeViewModel::class.java)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initBinding(view)
        initObserver()
        initRecycler()
        netscrollListener()

        binding.buttonReload.setOnClickListener {  viewModel.getListMarvel()}
    }

    private fun initBinding(view: View) {
        _binding = FragmentHomeBinding.bind(view)
    }

    private fun initObserver() {
        viewModel.respondCharacter.observe(viewLifecycleOwner, Observer { respondCharacterList(it)  })
        viewModel.respondCharacterData.observe(viewLifecycleOwner, Observer { respondCharacterData(it)  })
        lifecycle.addObserver(viewModel)
    }

    private fun initRecycler(){
        binding.recyclerCharacter.setHasFixedSize(true)
        binding.recyclerCharacter.layoutManager = GridLayoutManager(context,3)
        binding.recyclerCharacter.addItemDecoration(
            DecoratorGridRecycler(context!!.resources
                .getDimension(R.dimen.marginMoreLittle).toInt(),3)
        )
    }

    private fun netscrollListener(){
        binding.netscrollHome.setOnScrollChangeListener(NestedScrollView.OnScrollChangeListener {
                v, scrollX, scrollY, oldScrollX, oldScrollY ->
            if (scrollY == v.getChildAt(0).measuredHeight - v.measuredHeight) {
                if (binding.progressAnimation.visibility == View.GONE)
                {
                    viewModel.getListMarvel()
                    isUpdateList(true)
                }
            }
        })
    }

    //region RESPOND
    private fun respondCharacterList(resp:RespondCharacter){
        if (BodyApiManager.basicResponse(activity!!,resp.code,resp.message))
        {
            imageStatus(0)
            resp.data?.results?.let {
                isUpdateList(false)
                imageStatus(0)
                setDataToList(it)
            }
        }
        else
        {
            imageStatus(1)
        }
    }

    private fun respondCharacterData(resp:RespondCharacter){
        VisibilityManager.showProgress(activity!!,false)
        if (BodyApiManager.basicResponse(activity!!,resp.code,resp.message))
        {
            imageStatus(0)
            resp.data?.results?.first()?.let {
                val bottomSheet = BottomSheetCharacter.initData(
                    it,
                    object :EventUpdateFav{
                        override fun upDate() {

                        }
                    })
                bottomSheet.show(activity!!.supportFragmentManager, "BOTTOM_CHARACTER")
            }
        }
    }

    //endregion

    private fun setDataToList(list:MutableList<DataCharacters>){
        if (listCharacter.isNullOrEmpty())
        {
            listCharacter = list
            adapter.adapterConstructor(list,object : EventTapCharacter{
                override fun tapCharacter(data: DataCharacters) {
                    VisibilityManager.showProgress(activity!!,true)
                    viewModel.getDataCharacter(data.id.toString())
                }
            })
            binding.recyclerCharacter.adapter = adapter
        }
        else
        {
            listCharacter.addAll(list)
            adapter.notifyDataSetChanged()
        }
    }

    private fun imageStatus(status:Int){
        when(status){
            0->{
                binding.buttonReload.visibility = View.GONE
                binding.containerStatus.setAnimationFadeOut(300)
                binding.progressAnimationStatus.pauseAnimation()
            }
            1->{
                binding.buttonReload.setAnimationFadeIn(300)
                binding.progressAnimationStatus.pauseAnimation()
                binding.textStatus.text = getString(R.string.something_failed)
            }
            2->{
                binding.buttonReload.visibility = View.GONE
                binding.containerStatus.setAnimationFadeIn(300)
                binding.progressAnimationStatus.playAnimation()
                binding.textStatus.text = getString(R.string.looking)
            }
        }
    }

    private fun isUpdateList(ban:Boolean){
        if (ban)
        {
            binding.progressAnimation.setAnimationFadeIn(300)
            binding.progressAnimation.playAnimation()
        }
        else
        {
            binding.progressAnimation.setAnimationFadeOut(300)
            binding.progressAnimation.cancelAnimation()
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}


