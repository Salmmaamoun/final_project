package com.example.graduation_project.ui.bottomnavigationScreens.quran.quranPage

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.graduation_project.R
import com.example.graduation_project.databinding.FragmentPageQuraanBinding
import com.example.graduation_project.ui.base.BaseFragment
import com.example.graduation_project.ui.bottomnavigationScreens.quran.tafseer.TafseerBottomSheetFragment


class QuraanPageFragment :BaseFragment<FragmentPageQuraanBinding>{
    private var pageNumber:Int=0

    private lateinit var quranViewModel:QuranViewModel

    constructor(pageNumber: Int) : super() {
        this.pageNumber = pageNumber
        Log.d("cons",pageNumber.toString())
    }
    fun getPageNumber(): Int {
        return pageNumber
        Log.d("pagenum",pageNumber.toString())
    }

    override val layoutFragmentId: Int
        get() = R.layout.fragment_page_quraan
    override val viewModel: ViewModel
        get() = TODO("Not yet implemented")




    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        quranViewModel = ViewModelProvider(this).get(QuranViewModel::class.java)

        val quranPageBitmap = quranViewModel.getQuranImageByPageNumber(requireContext(), pageNumber)
        if (quranPageBitmap != null) {
            binding.quranPage.setImageBitmap(quranPageBitmap)
            Log.d("salma", "Quran page loaded successfully.")
        } else {
            Log.e("salma", "Failed to load Quran page.")
        }
        binding.btnTfseer.setOnClickListener {
            val bottomSheet = TafseerBottomSheetFragment()
            val args = Bundle()
            args.putInt("pageNumber", getPageNumber())
            bottomSheet.arguments = args
            bottomSheet.show(parentFragmentManager, bottomSheet.tag)
            Log.d("sheet", "successfully.")
        }
    }


}