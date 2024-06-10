package com.example.graduation_project.ui.bottomnavigationScreens.quran
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.graduation_project.databinding.ListItemSoraBinding
import com.example.graduation_project.ui.bottomnavigationScreens.quran.data.pojo.quran.Sora


class SoraListAdapter(private val index: List<Sora>, private val itemClickListener: SoraItemClickListener) : RecyclerView.Adapter<SoraListAdapter.ViewHolderList>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolderList {
        val binding = ListItemSoraBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ViewHolderList(binding)
    }

    override fun onBindViewHolder(holder: ViewHolderList, position: Int) {
        val item = index[position]
        holder.bindSora(item)
        holder.itemView.setOnClickListener { itemClickListener?.onSoraItemClicked(item) }
    }

    override fun getItemCount(): Int {
        return index.size
    }

    inner class ViewHolderList(private val binding: ListItemSoraBinding):RecyclerView.ViewHolder(binding.root) {
        private val soraNumber: TextView = binding.soraNumber
        private val soraName: TextView = binding.soraName


        fun bindSora(sora: Sora) {
            soraNumber.text = sora.soraNumber.toString()
            soraName.text =sora.arabicName+"::"+sora.englishName+" - "
          //  from.text = sora.startPage.toString()
           // to.text = sora.endPage.toString()
            // Bind other views as needed
        }
    }
    interface SoraItemClickListener {
        fun onSoraItemClicked(sora: Sora)
    }
}




