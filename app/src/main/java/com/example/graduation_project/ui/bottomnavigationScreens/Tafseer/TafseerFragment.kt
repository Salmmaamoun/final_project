package com.example.graduation_project.ui.bottomnavigationScreens.Tafseer

import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Toast
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.data.remote.LoginRegiisterRetrofitInstance
import com.example.data.repo.datasource.DataSourceImp
import com.example.data.repo.repo.RepoImp
import com.example.domain.usecase.AyaUseCase
import com.example.graduation_project.R
import com.example.graduation_project.databinding.FragmentTafseerBinding
import com.example.graduation_project.ui.base.BaseFragment
import com.example.graduation_project.ui.bottomnavigationScreens.quran.QuraanContainer.QuranContainerFragment

class TafseerFragment : BaseFragment<FragmentTafseerBinding>() {
    lateinit var surahName: String
    lateinit var surahNumber: String
    private lateinit var tafseerViewModel: TafseerViewModel
    override val layoutFragmentId: Int
        get() = R.layout.fragment_tafseer
    override val viewModel: ViewModel
        get() = TODO("Not yet implemented")

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.nameSura.text = "اختر اسم السورة".toString()
        val apiService = LoginRegiisterRetrofitInstance.getApi()
        val dataSource= apiService?.let { DataSourceImp(it) }
        val repository = RepoImp(dataSource)
        val useCase = AyaUseCase(repository)
        val viewModelFactory = TaseerViewModelFactory(useCase)
        tafseerViewModel =
            ViewModelProvider(this, viewModelFactory).get(TafseerViewModel::class.java)
        tafseerViewModel.ayahData.observe(viewLifecycleOwner) { tafseerData ->
            binding.aya.text=tafseerData?.ayah?.text
            binding.tafseerResult.text =   tafseerData?.data

        }

        tafseerViewModel.isLoading.observe(viewLifecycleOwner, Observer { isLoading ->
            if (isLoading) {
                // Show loading state
                showLoadingState()
            } else {
                // Hide loading state
                hideLoadingState()
            }
        })
        setView()
    }

    fun setView() {
        val args = arguments
        if (args != null) {
            surahName = args.getString("dataItem1").toString()
            surahNumber = args.getString("dataItem2").toString()

            if (surahName.isNullOrEmpty() || surahNumber.isNullOrEmpty()) {
                // Handle the case when surahName or surahNumber is null or empty
                binding.surahNameEd.text = "اسم السورة ليس موجود..."
            } else {
                binding.surahNameEd.text = "$surahNumber - $surahName"
            }
        } else {
            // Handle the case when arguments are null
            binding.surahNameEd.text = "اسم السورة ليس موجود..."
        }

        binding.searchButtonAr.setOnClickListener {
            val ayaNumber = binding.numberAya.text.toString().trim()

            if (ayaNumber.isNotEmpty()) {
                if (binding.surahNameEd.text.isNullOrEmpty()) {
                    // Handle the case when surahNameEd is null or empty
                    Toast.makeText(requireContext(), "Surah name is missing", Toast.LENGTH_SHORT).show()
                } else {
                    tafseerViewModel.fetchAyah(1, surahNumber.toInt(), ayaNumber.toInt())
                    Log.d("tafseer1", surahName + " " + surahNumber + " " + ayaNumber)
                }
            } else {
                // Handle the case when ayaNumber is empty
                Toast.makeText(requireContext(), "Please enter a valid aya number", Toast.LENGTH_SHORT).show()
            }
        }
        binding.searchButtonEn.setOnClickListener {
            val ayaNumber = binding.numberAya.text.toString().trim()

            if (ayaNumber.isNotEmpty()) {
                if (binding.surahNameEd.text.isNullOrEmpty()) {
                    // Handle the case when surahNameEd is null or empty
                    Toast.makeText(requireContext(), "Surah name is missing", Toast.LENGTH_SHORT).show()
                } else {
                    tafseerViewModel.fetchAyah(17, surahNumber.toInt(), ayaNumber.toInt())
                    Log.d("tafseer1", surahName + " " + surahNumber + " " + ayaNumber)
                }
            } else {
                // Handle the case when ayaNumber is empty
                Toast.makeText(requireContext(), "Please enter a valid aya number", Toast.LENGTH_SHORT).show()
            }
        }

        binding.nameSura.setOnClickListener {
            val fragment = SurahNameFragment()
            parentFragmentManager.beginTransaction()
                .replace(R.id.frame_container, fragment)
                .commit()
        }
    }
    private fun showLoadingState() {
        // Disable buttons
        binding.nameSura.isEnabled = false
        binding.numberAya.isEnabled = false
        binding.searchButtonAr.isEnabled = false
        binding.searchButtonEn.isEnabled = false

        binding.loadingIndicator.visibility = View.VISIBLE
    }

    private fun hideLoadingState() {
        // Enable buttons
        binding.nameSura.isEnabled = true
        binding.numberAya.isEnabled =true
        binding.searchButtonAr.isEnabled =true
        binding.searchButtonEn.isEnabled =true
        binding.loadingIndicator.visibility = View.GONE
    }
}