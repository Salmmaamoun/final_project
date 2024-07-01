package com.example.graduation_project.ui.bottomnavigationScreens.home.seimantic

import android.annotation.SuppressLint
import android.app.Application
import android.graphics.Color
import android.graphics.Paint
import android.text.SpannableString
import android.text.Spanned
import android.text.style.ForegroundColorSpan
import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.lifecycle.LifecycleOwner
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.data.remote.LoginRegiisterRetrofitInstance
import com.example.data.repo.datasource.DataSourceImp
import com.example.data.repo.repo.RepoImp
import com.example.domain.entity.AyaData
import com.example.domain.entity.SemanticApiResponse
import com.example.domain.usecase.SearchSemanticUseCase
import com.example.graduation_project.databinding.ItemSemanticAyahBinding
class Adapter(val onHighligthListener: (AyaData, Int) -> Unit) : ListAdapter<AyaData, Adapter.ViewHolder>(DiffUtil()) {

    var question = ""

    inner class ViewHolder(private val binding: ItemSemanticAyahBinding) :
        RecyclerView.ViewHolder(binding.root) {

        @SuppressLint("SetTextI18n")
        fun bind(item: AyaData, position: Int) {
            binding.apply {
                binding.executePendingBindings()

                if (item.isHighligthed) {
                    val originalText = item.verseWithoutTashkeel
                    binding.aya.text = originalText
                    val start = item.highligthedResponse?.start ?: 0
                    val end = item.highligthedResponse?.end ?: 0

                    // Ensure the indices are within the valid range
                    if (originalText != null) {
                        if (start in 0 until originalText.length && end in 0..originalText.length && start < end) {
                            val spannableString = SpannableString(originalText)
                            val colorSpan = ForegroundColorSpan(Color.YELLOW) // Highlight color
                            spannableString.setSpan(colorSpan, start, end, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE)
                            binding.aya.text = spannableString
                        } else {
                            binding.aya.text = originalText // Fallback to original text if indices are invalid
                        }
                    }
                } else {
                    binding.aya.text = item.verse
                    binding.surahId.text = item.surah
                    binding.ayanum.text = item.numberInSurah.toString()
                }

                binding.highligthbtn.setOnClickListener {
                    onHighligthListener(item, position)
                }
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding = ItemSemanticAyahBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ViewHolder(binding)
    }

    override fun onViewRecycled(holder: ViewHolder) {
        super.onViewRecycled(holder)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val currentItem = getItem(position)
        holder.bind(currentItem, position)
    }
}

class DiffUtil : DiffUtil.ItemCallback<AyaData>() {

    override fun areItemsTheSame(oldItem: AyaData, newItem: AyaData): Boolean {
        return oldItem == newItem
    }

    override fun areContentsTheSame(oldItem: AyaData, newItem: AyaData): Boolean {
        return oldItem == newItem
    }
}
