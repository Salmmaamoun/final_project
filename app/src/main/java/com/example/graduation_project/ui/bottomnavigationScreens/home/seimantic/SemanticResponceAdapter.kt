package com.example.graduation_project.ui.bottomnavigationScreens.home.seimantic


import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.domain.entity.SemanticAiResponce
import com.example.graduation_project.databinding.ItemAyahBinding
/*
class SemanticResponceAdapter(private var items: SemanticAiResponce) :
    RecyclerView.Adapter<SemanticResponceAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding = ItemAyahBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item = items.results[position]
        holder.bind(item)
    }

    override fun getItemCount() = items.results.size

    inner class ViewHolder(private val binding: ItemAyahBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bind(item: Triple<Long ,Long,String>?) {

            binding.ayah.text

            binding.ayah.text = item?.third
            binding.executePendingBindings()
        }
    }

    fun updateItems(newItems: SemanticAiResponce) {
        this.items = newItems
        notifyDataSetChanged()
    }


}
*/
