package com.example.graduation_project.ui.bottomnavigationScreens.home

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.domain.entity.Aya
import com.example.graduation_project.databinding.FragmentHomeBinding
import com.example.graduation_project.databinding.ItemAyahBinding


class HomeFragment :  Fragment() {

    private val searchLexicalViewModel: SearchLexicalViewModel by viewModels()
    private lateinit var binding: FragmentHomeBinding
    private lateinit var adapter: AyahAdapter // Create your RecyclerView adapter

    class AyahAdapter : ListAdapter<Aya, AyahViewHolder>(AyahDiffCallback()) {

        override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): AyahViewHolder {
            val inflater = LayoutInflater.from(parent.context)
            val binding = ItemAyahBinding.inflate(inflater, parent, false)
            return AyahViewHolder(binding)
        }

        override fun onBindViewHolder(holder: AyahViewHolder, position: Int) {
            val ayah = getItem(position)
            holder.bind(ayah)
        }
    }

    class AyahViewHolder(private val binding: ItemAyahBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bind(ayah: Aya) {
            binding.ayah = ayah
            binding.executePendingBindings()
        }
    }

    class AyahDiffCallback : DiffUtil.ItemCallback<Aya>() {
        override fun areItemsTheSame(oldItem: Aya, newItem: Aya): Boolean {
            return oldItem.id == newItem.id
        }

        override fun areContentsTheSame(oldItem: Aya, newItem: Aya): Boolean {
            return oldItem == newItem
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentHomeBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        // Initialize RecyclerView
        adapter = AyahAdapter() // Create your RecyclerView adapter
        binding.recyclerView.layoutManager = LinearLayoutManager(requireContext())
        binding.recyclerView.adapter = adapter

        // Set up search button click listener
        binding.linearLayout.setOnClickListener {
            val searchTerm = binding.searchEditText.text.toString()
            searchLexicalViewModel.searchLexical(searchTerm)
        }

        // Observe search results
        lifecycleScope.launchWhenStarted {
            searchLexicalViewModel.searchResults.collect { ayahs ->
                adapter.submitList(ayahs)
            }
        }
    }


}