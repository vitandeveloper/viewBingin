package com.example.marlonviana.fragments

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.GridLayoutManager
import com.example.marlonviana.R
import com.example.marlonviana.adapter.AdapterCharacter
import com.example.marlonviana.adapter.DecoratorGridRecycler
import com.example.marlonviana.databinding.FragmentFavBinding
import com.example.marlonviana.dialog.BottomSheetCharacter
import com.example.marlonviana.event.EventTapCharacter
import com.example.marlonviana.event.EventUpdateFav
import com.example.marlonviana.model.DataCharacters
import com.example.marlonviana.util.setAnimationFadeIn
import com.example.marlonviana.util.setAnimationFadeOut
import com.example.marlonviana.viewmodel.FavViewModel


class FragmentFav : Fragment(R.layout.fragment_fav) {
    private var _binding: FragmentFavBinding? = null
    private val binding get() = _binding!!
    private val viewModel by lazy {
        ViewModelProvider(this).get(FavViewModel::class.java)
    }

    override fun onHiddenChanged(hidden: Boolean) {
        super.onHiddenChanged(hidden)
        if (!hidden) {
            viewModel.getFavList()
        }
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        initBinding(view)
        initObserver()
        initRecycler()
    }

    private fun initBinding(view: View){
        _binding = FragmentFavBinding.bind(view)
    }

    private fun initObserver() {
        viewModel.respondCharacter.observe(viewLifecycleOwner, { respondCharacterList(it) })
        lifecycle.addObserver(viewModel)
    }

    private fun initRecycler(){
        binding.recyclerCharacter.setHasFixedSize(true)
        binding.recyclerCharacter.layoutManager = GridLayoutManager(context, 3)
        binding.recyclerCharacter.addItemDecoration(DecoratorGridRecycler(context!!.resources
                    .getDimension(R.dimen.marginMoreLittle).toInt(), 3))
    }

    //region RESPOND
    private fun respondCharacterList(list: MutableList<DataCharacters>){
        if (list.isNotEmpty())
        {
            imageStatusShow(1)
        }
        else
        {
            imageStatusShow(0)
        }

        val adapter =  AdapterCharacter()
        adapter.adapterConstructor(list, object : EventTapCharacter {
            override fun tapCharacter(data: DataCharacters) {
                val bottomSheet = BottomSheetCharacter.initData(
                    data,
                    object :EventUpdateFav{
                        override fun upDate() {
                            viewModel.getFavList()
                        }
                    })
                bottomSheet.show(activity!!.supportFragmentManager, "BOTTOM_CHARACTER")
            }
        })
        binding.recyclerCharacter.adapter = adapter
    }
    //endregion

    private fun imageStatusShow(status: Int){
        when(status){
            0 ->{
                binding.containerStatus.setAnimationFadeIn(500)
            }
            1 -> {
                binding.containerStatus.setAnimationFadeOut(200)
            }
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}