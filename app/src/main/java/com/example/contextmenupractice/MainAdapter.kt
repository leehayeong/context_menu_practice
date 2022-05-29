package com.example.contextmenupractice

import android.annotation.SuppressLint
import android.view.ContextMenu
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
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

    // recyclerview 에서 contextmenu 를 사용하는 법 -> 뷰홀더에서 ContextMenuListener 상속받음
    inner class MainViewHolder(
        private val binding: ItemListBinding
    ) : RecyclerView.ViewHolder(binding.root), View.OnCreateContextMenuListener {

        init {
            // context menu listener 등록
            binding.root.setOnCreateContextMenuListener(this)
        }

        override fun onCreateContextMenu(
            menu: ContextMenu?,
            view: View?,
            menuInfo: ContextMenu.ContextMenuInfo?
        ) {
            // context menu 설정
            menu?.setHeaderTitle("타이틀")
            var item1 = menu?.add(0, 0, 0, "선택1")?.setOnMenuItemClickListener {
                Toast.makeText(binding.root.context, "선택1", Toast.LENGTH_SHORT).show()
                true
            }

            var item2 = menu?.add(0, 1, 1, "선택2")?.setOnMenuItemClickListener {
                Toast.makeText(binding.root.context, "선택2", Toast.LENGTH_SHORT).show()
                true
            }
        }

        fun bind(item: String) {
            binding.text = item
        }
    }
}