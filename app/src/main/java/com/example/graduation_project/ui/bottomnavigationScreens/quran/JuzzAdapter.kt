package com.example.graduation_project.ui.bottomnavigationScreens.quran

import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.graduation_project.databinding.ListItemSoraBinding
import com.example.graduation_project.ui.bottomnavigationScreens.quran.data.pojo.quran.Juzz

class JuzzAdapter (private val index: List<Juzz>, private val itemClickListener: SoraItemClickListener) : RecyclerView.Adapter<JuzzAdapter.ViewHolderList>() {



    interface SoraItemClickListener {
        fun onSoraItemClicked(sora: Juzz)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolderList {
        val binding = ListItemSoraBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ViewHolderList(binding)
    }

    override fun getItemCount(): Int {
        return index.size
    }

    override fun onBindViewHolder(holder: ViewHolderList, position: Int) {
        val item = index[position]
        holder.bindSora(item)
        holder.itemView.setOnClickListener { itemClickListener?.onSoraItemClicked(item) }
    }
    inner class ViewHolderList(private val binding: ListItemSoraBinding):RecyclerView.ViewHolder(binding.root) {
        private val soraNumber: TextView = binding.soraNumber
        private val soraName: TextView = binding.soraName
        /* private val from: TextView = binding.soraStart
         private val to: TextView = binding.soraEnd
         private val wordTo: TextView = binding.wordTo
         private val wordFrom: TextView = binding.wordFrom*/

        fun bindSora(juzz: Juzz) {
            soraNumber.text = "جزء رقم "+juzz.jozzNumber.toString()
            soraName.text = "من صفحة : "+ juzz.startPage.toString() +" الى صفحة :"+juzz.endPage.toString()
            //  from.text = sora.startPage.toString()
            // to.text = sora.endPage.toString()
            // Bind other views as needed
        }
    }
}




