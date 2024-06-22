package com.example.graduation_project.ui.bottomnavigationScreens.quran.tafseer

import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.RecyclerView.Adapter
import androidx.recyclerview.widget.RecyclerView.ViewHolder
import com.example.graduation_project.R
import com.example.graduation_project.databinding.ItemTafseerBinding
import com.example.graduation_project.databinding.ListItemSoraBinding
import com.example.graduation_project.ui.bottomnavigationScreens.quran.data.pojo.quran.Aya
import com.example.graduation_project.ui.bottomnavigationScreens.quran.data.pojo.quran.Tfseer

class TfseerAdapter : RecyclerView.Adapter<TfseerAdapter.TfseerViewHolder>() {

    private var tfseerList: List<Tfseer> = emptyList()
    private var ayaList: List<Aya> = emptyList()

    inner class TfseerViewHolder(val binding: ItemTafseerBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bind(tfseer: Tfseer, aya: Aya) {
            binding.searchAya.text = tfseer.text
            binding.searchAyaNm.text = aya.aya_text
            binding.searchSora.text = "سورة ${aya.sora_name_ar} آية رقم: ${aya.aya_no}"
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TfseerViewHolder {
        val binding = ItemTafseerBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return TfseerViewHolder(binding)
    }

    override fun getItemCount(): Int {
        return tfseerList.size
    }

    override fun onBindViewHolder(holder: TfseerViewHolder, position: Int) {
        if (tfseerList.isNotEmpty() && ayaList.isNotEmpty()) {
            val tfseer = tfseerList[position]
            val aya = ayaList[position]
            holder.bind(tfseer, aya)
            Log.d("test", "Binding tfseer: $tfseer and aya: $aya at position: $position")
        }
    }

    fun updateData(tfseerList: List<Tfseer>, ayaList: List<Aya>) {
        this.tfseerList = tfseerList
        this.ayaList = ayaList
        notifyDataSetChanged()
        Log.d("test", "Data updated in adapter with tfseerList: $tfseerList and ayaList: $ayaList")
    }
}