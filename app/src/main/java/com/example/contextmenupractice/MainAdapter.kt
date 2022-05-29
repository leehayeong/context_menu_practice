package com.example.contextmenupractice

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.contextmenupractice.databinding.ItemListBinding

class MainAdapter : RecyclerView.Adapter<MainAdapter.MainViewHolder>() {

    private var items: MutableList<String> = mutableListOf()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MainViewHolder {
        val binding = ItemListBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return MainViewHolder(binding)
    }

    override fun onBindViewHolder(holder: MainViewHolder, position: Int) {
        holder.bind(items[position])
    }

    override fun getItemCount(): Int {
        return items.size
    }

    @SuppressLint("NotifyDataSetChanged")
    fun setItems(items: MutableList<String>) {
        this.items = items
        notifyDataSetChanged()
    }

    inner class MainViewHolder(
        private val binding: ItemListBinding
    ) : RecyclerView.ViewHolder(binding.root) {

        fun bind(item: String) {
            binding.text = item
        }
    }
}