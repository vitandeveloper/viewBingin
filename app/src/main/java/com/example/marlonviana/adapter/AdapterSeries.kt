package com.example.marlonviana.adapter

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.marlonviana.R
import com.example.marlonviana.databinding.ItemCharacterBinding
import com.example.marlonviana.databinding.ItemSeriesBinding
import com.example.marlonviana.model.ItemComics


class AdapterSeries: RecyclerView.Adapter<AdapterSeries.ViewHolder>() {

    private var items: MutableList<ItemComics>  = ArrayList()

    fun adapterConstructor(items : MutableList<ItemComics>){
        this.items = items
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding = ItemSeriesBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ViewHolder(binding)
    }

    class ViewHolder(binding: ItemSeriesBinding) : RecyclerView.ViewHolder(binding.root) {
        val binding = binding
    }

    @SuppressLint("NewApi", "SetTextI18n")
    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.binding.textSerie.text = items[position].name
    }

    override fun getItemCount(): Int {
        return items.size
    }

    override fun getItemViewType(position: Int): Int {
        return position
    }
}