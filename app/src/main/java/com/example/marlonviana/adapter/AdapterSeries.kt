package com.example.marlonviana.adapter

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.marlonviana.R
import com.example.marlonviana.model.ItemComics


class AdapterSeries: RecyclerView.Adapter<AdapterSeries.ViewHolder>() {

    private var items: MutableList<ItemComics>  = ArrayList()

    fun adapterConstructor(items : MutableList<ItemComics>){
        this.items = items
    }

    class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val textSerie = view.findViewById(R.id.textSerie) as TextView
    }

    @SuppressLint("NewApi", "SetTextI18n")
    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.textSerie.text = items[position].name
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        return ViewHolder(layoutInflater.inflate(R.layout.item_series, parent, false))
    }

    override fun getItemCount(): Int {
        return items.size
    }

    override fun getItemViewType(position: Int): Int {
        return position
    }
}