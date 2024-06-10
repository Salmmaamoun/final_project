package com.example.graduation_project.ui.bottomnavigationScreens.quran.QuraanContainer

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModel
import androidx.viewpager2.widget.ViewPager2
import com.example.graduation_project.R
import com.example.graduation_project.databinding.FragmentQuranContainerBinding
import com.example.graduation_project.ui.base.BaseFragment
import com.example.graduation_project.ui.bottomnavigationScreens.quran.tafseer.TafseerBottomSheetFragment
import com.example.graduation_project.ui.bottomnavigationScreens.quran.tafseer.TfseerAdapter


class QuranContainerFragment : BaseFragment<FragmentQuranContainerBinding>() {
    private lateinit var viewPager: ViewPager2
    private val tfseerAdapter = TfseerAdapter()
    private var startPage: Int = 0
    private var currentPage: Int = 0 // Track the current page
    override val layoutFragmentId: Int
        get() =R.layout.fragment_quran_container
    override val viewModel: ViewModel
        get() = TODO("Not yet implemented")

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val args = arguments
        startPage = args?.getInt("startPage", 1) ?: 1
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val pagerAdapter = QuraanPageAdapter(this)

        binding.quranPager.adapter = pagerAdapter
        binding.quranPager.setCurrentItem(604 - startPage, false)

        // Update currentPage when the page changes
        binding.quranPager.registerOnPageChangeCallback(object : ViewPager2.OnPageChangeCallback() {
            override fun onPageSelected(position: Int) {
                super.onPageSelected(position)
                currentPage = position + startPage // Adjust for the starting page
                Log.d("Sal", currentPage.toString()) // Immediate logging of currentPage
            }
        })

        binding.btnTfseer.setOnClickListener {
            showTafseerBottomSheet()
        }

    }

    private fun showTafseerBottomSheet() {
        val bottomSheet = TafseerBottomSheetFragment()
        val args = Bundle()
        args.putInt("pageNumber", 604-binding.quranPager.currentItem ) // Use ViewPager2's currentItem
        bottomSheet.arguments = args
        bottomSheet.show(parentFragmentManager, bottomSheet.tag)


    }
}
