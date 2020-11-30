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
import com.example.marlonviana.databinding.ActivityMainBinding.inflate
import com.example.marlonviana.databinding.BottomshetCharacterBinding.inflate
import com.example.marlonviana.databinding.ItemCharacterBinding
import com.example.marlonviana.databinding.ViewProgressBarBinding.inflate
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

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding = ItemCharacterBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ViewHolder(binding)
    }

    class ViewHolder(binding: ItemCharacterBinding) : RecyclerView.ViewHolder(binding.root) {
        val binding = binding
    }

    @SuppressLint("NewApi", "SetTextI18n")
    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.binding.textComic.text = items[position].name
        holder.binding.textComicDescrip.text = items[position].description

        Glide.with(AppMaster.contextApp())
            .load(items[position].thumbnail?.path+"."+items[position].thumbnail?.extension)
            .into(holder.binding.imageComic)

        holder.binding.root.setOnClickListener {
            holder.itemView.setAnimationPushButton()
            eventTapCharacter.tapCharacter(items[position])
        }
    }

    override fun getItemCount(): Int {
        return items.size
    }

    override fun getItemViewType(position: Int): Int {
        return position
    }
}