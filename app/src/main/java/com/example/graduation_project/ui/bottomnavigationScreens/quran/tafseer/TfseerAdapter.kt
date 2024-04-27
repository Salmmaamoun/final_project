package com.example.graduation_project.ui.bottomnavigationScreens.quran.tafseer

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView.Adapter
import androidx.recyclerview.widget.RecyclerView.ViewHolder
import com.example.graduation_project.R
import com.example.graduation_project.databinding.ItemTafseerBinding
import com.example.graduation_project.databinding.ListItemSoraBinding
import com.example.graduation_project.ui.bottomnavigationScreens.quran.data.pojo.quran.Aya
import com.example.graduation_project.ui.bottomnavigationScreens.quran.data.pojo.quran.Tfseer

class TfseerAdapter : Adapter<TfseerAdapter.TfseerViewHolder>() {

    var list:List<Tfseer> = ArrayList()
    var listAya:List<Aya> = ArrayList()

    inner class TfseerViewHolder(val binding: ItemTafseerBinding) : ViewHolder(binding.root){
        fun bind(tfseer:Tfseer,aya:Aya){
            binding.searchAya.text=tfseer.text
            binding.searchAyaNm.text =aya.aya_text
          binding.searchSora.text =
                "سوة "+aya.sora_name_ar+" آيه رقم :  "+aya.aya_no.toString()

        }


    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TfseerViewHolder {
        val binding = ItemTafseerBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return TfseerViewHolder(binding)
    }


    override fun getItemCount(): Int {
        return listAya.size
    }

    override fun onBindViewHolder(holder: TfseerViewHolder, position: Int) {
        val tfseerAya = list[position]
        val pageDetails = listAya[position]
        holder.bind(tfseerAya,pageDetails)


    }
    fun updateData(tfseerList: List<Tfseer>, listAya: List<Aya>) {
        this.list = tfseerList
        this.listAya = listAya
        notifyDataSetChanged()
    }
}
