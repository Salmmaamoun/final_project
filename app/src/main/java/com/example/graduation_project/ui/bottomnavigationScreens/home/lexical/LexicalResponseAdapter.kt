package com.example.graduation_project.ui.bottomnavigationScreens.home.lexical

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.domain.entity.LexicalResponseItem
import com.example.graduation_project.databinding.ItemAyahBinding

class LexicalResponseAdapter(private var items: List<LexicalResponseItem?>) :
    RecyclerView.Adapter<LexicalResponseAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding = ItemAyahBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item = items[position]
        holder.bind(item)
    }

    override fun getItemCount() = items.size

    inner class ViewHolder(private val binding: ItemAyahBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bind(item: LexicalResponseItem?) {
            binding.ayah = item?.ayahs?.firstOrNull()
            binding.name=item

            binding.executePendingBindings()
        }
    }

    fun updateItems(newItems: List<LexicalResponseItem?>) {
        this.items = newItems
        notifyDataSetChanged()
    }
}
