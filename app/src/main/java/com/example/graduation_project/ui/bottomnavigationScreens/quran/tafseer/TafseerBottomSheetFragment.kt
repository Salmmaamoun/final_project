package com.example.graduation_project.ui.bottomnavigationScreens.quran.tafseer

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import com.example.graduation_project.R
import com.example.graduation_project.databinding.BottomSheetBinding
import com.example.graduation_project.ui.bottomnavigationScreens.quran.data.pojo.quran.Aya
import com.example.graduation_project.ui.bottomnavigationScreens.quran.data.pojo.quran.Tfseer
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.coroutineScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
class TafseerBottomSheetFragment : BottomSheetDialogFragment() {
    private lateinit var binding: BottomSheetBinding
    private val viewModel: TfseerViewModel by lazy {
        ViewModelProvider(requireActivity()).get(TfseerViewModel::class.java)
    }
    private lateinit var adapter: TfseerAdapter
    private var pageNumber: Int = 0
    private val ayaList = mutableListOf<Aya>()
    private val tfseerList = mutableListOf<Tfseer>()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = DataBindingUtil.inflate(
            inflater,
            R.layout.bottom_sheet,
            container,
            false
        )
        val args = arguments
        pageNumber = args?.getInt("pageNumber", 0) ?: 0
        Log.d("test", "Page number: $pageNumber")

        // Fetch data and update the adapter
        observeDataLoading()

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setUpRv()
    }

    private fun observeDataLoading() {
        viewModel.dataLoaded.observe(viewLifecycleOwner) { isLoaded ->
            if (isLoaded) {
                getPageDataFromDB()
            }
        }
    }

    private fun getPageDataFromDB() {
        viewModel.getTfseerAyaByPage(pageNumber)?.observe(viewLifecycleOwner) { ayaList ->
            this.ayaList.clear()
            this.ayaList.addAll(ayaList)
            Log.d("test", "Received ayaList: $ayaList")

            // Fetch tfseer immediately
            viewLifecycleOwner.lifecycleScope.launch {
                getTfseerAya(ayaList)
            }
        }
    }

    private suspend fun getTfseerAya(ayaList: List<Aya>) {
        withContext(Dispatchers.Main) {
            tfseerList.clear()
            ayaList.forEach { aya ->
                val tfseer = viewModel.getTfseerByPage(aya.sora.toString(), aya.aya_no.toString())
                if (tfseer != null) {
                    tfseerList.add(tfseer)
                }
            }
            adapter.updateData(tfseerList, ayaList)
            Log.d("test", "Adapter data updated with tfseerList: $tfseerList and ayaList: $ayaList")
        }
    }

    private fun setUpRv() {
        adapter = TfseerAdapter()
        binding.Tfseerrv.adapter = adapter
        Log.d("test", "RecyclerView set up with adapter")
    }
}

