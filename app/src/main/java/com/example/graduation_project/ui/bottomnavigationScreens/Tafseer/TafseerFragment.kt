package com.example.graduation_project.ui.bottomnavigationScreens.Tafseer

import android.os.Bundle
import android.util.Log
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
        binding.nameSura.text = "choise name surah".toString()
        val apiService = LoginRegiisterRetrofitInstance.getApi()
        val repository = RepoImp(apiService)
        val useCase = AyaUseCase(repository)
        val viewModelFactory = TaseerViewModelFactory(useCase)
        tafseerViewModel =
            ViewModelProvider(this, viewModelFactory).get(TafseerViewModel::class.java)
        tafseerViewModel.ayahData.observe(viewLifecycleOwner) { tafseerData ->
            binding.tafseerResult.text = tafseerData?.ayah?.text + ": \n" + tafseerData?.data

        }
        setView()
    }

    fun setView() {

        val args = arguments
        if (args != null) {
            surahName = args.getString("dataItem1").toString()
            surahNumber = args.getString("dataItem2").toString()

            if (surahName.isNullOrEmpty()) {
                binding.nameSura.text = "surah name"
            } else {
                binding.surahNameEd.text = surahNumber + "->" + surahName
            }
        } else {

        }





        binding.searchButton.setOnClickListener {
            val ayaNumber = binding.numberAya.text.toString()

            tafseerViewModel.fetchAyah(1, surahNumber.toInt(), ayaNumber.toInt())
            Log.d("tafseer1", surahName + " " + surahNumber + " " + ayaNumber)
        }

        binding.nameSura.setOnClickListener {
            val fragment = SurahNameFragment()
            parentFragmentManager.beginTransaction()
                .replace(R.id.frame_container, fragment)
                .commit()
        }


    }
}