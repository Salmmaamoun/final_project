package com.example.graduation_project.ui.bottomnavigationScreens.Tafseer

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.data.remote.LoginRegiisterRetrofitInstance
import com.example.data.repo.repo.RepoImp
import com.example.domain.entity.DataItem
import com.example.domain.usecase.AyaUseCase
import com.example.domain.usecase.SurahNameUseCase
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
        val args = arguments
        surahName = args?.getString("dataItem1").toString()
        surahNumber = args?.getString("dataItem2").toString()
        val apiService = LoginRegiisterRetrofitInstance.getApi()
        val repository = RepoImp(apiService)
        val useCase = AyaUseCase(repository)
        val viewModelFactory = TaseerViewModelFactory(useCase)
        tafseerViewModel =
            ViewModelProvider(this, viewModelFactory).get(TafseerViewModel::class.java)
        tafseerViewModel.ayahData.observe(viewLifecycleOwner) { tafseerData ->
            binding.tafseerResult.text = tafseerData
        }
        setView()
    }

    fun setView() {


        binding.nameSura.text = (surahNumber + "-> " + surahName).toString()

        binding.searchButton.setOnClickListener {
            // Retrieve ayah number from EditText
            val ayaNumber = binding.numberAya.text.toString()

            tafseerViewModel.fetchAyah(1, surahNumber.toInt(), ayaNumber.toInt())
        }

        binding.nameSura.setOnClickListener {
            val fragment = SurahNameFragment()
            parentFragmentManager.beginTransaction()
                .replace(R.id.frame_container, fragment)
                .commit()
        }
    }


}