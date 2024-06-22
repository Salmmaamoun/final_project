package com.example.graduation_project.ui.bottomnavigationScreens.quran.tafseer

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModelProvider
import com.example.graduation_project.R
import com.example.graduation_project.databinding.BottomSheetBinding
import com.example.graduation_project.ui.bottomnavigationScreens.quran.data.pojo.quran.Aya
import com.example.graduation_project.ui.bottomnavigationScreens.quran.data.pojo.quran.Tfseer
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class TafseerBottomSheetFragment:BottomSheetDialogFragment() {
   lateinit  var binding: BottomSheetBinding
    private lateinit var viewModel: TfseerViewModel
    private lateinit var adapter: TfseerAdapter
    private val tfseerList = ArrayList<Tfseer>()
    var  pageNumber:Int=0

    //val args: TafseerBottomSheetFragment by navArgs()
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = DataBindingUtil.inflate(
            inflater,
            R.layout.bottom_sheet,
            container,
            false
        )
        val args = arguments
        pageNumber = args?.getInt("pageNumber", 0) ?: 0
        Log.d("test", pageNumber.toString())
        return binding.root
    }
    private fun getPageDataFromDB() {
        viewModel.getTfseerAyaByPage( pageNumber)
            .observe(viewLifecycleOwner) {
                getTfseerAya(it)
                registerTfseerAyaCallBack()
            }
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel = ViewModelProvider(requireActivity()).get(TfseerViewModel::class.java)
        Log.d("test", "on onViewCreated tfseer")
//        testLiveData()
        setUpRv()
        getPageDataFromDB()
    }
    private fun getTfseerAya(list: List<Aya>) {
        viewModel.startWork = {
            list.forEach { aya ->
                viewModel.getTfseerByPage(
                    aya.sora.toString(),
                    aya.aya_no.toString()
                )
            }
            getTfseerDataFromViewModel(list)

        }
    }
    private fun registerTfseerAyaCallBack(){
        viewModel.tfseerCallback= {
            tfseerList.add(it)
        }
    }
    private fun getTfseerDataFromViewModel(list: List<Aya>) {
        CoroutineScope(Dispatchers.Main).launch {
            if (tfseerList.size == list.size) {
                adapter.list = tfseerList
                adapter.listAya = list
                adapter.updateData(tfseerList, list)
            }
        }
    }

    private fun setUpRv() {
        adapter = TfseerAdapter()
        binding.TfseerRv.apply {
            adapter = this@TafseerBottomSheetFragment.adapter

        }
    }




}