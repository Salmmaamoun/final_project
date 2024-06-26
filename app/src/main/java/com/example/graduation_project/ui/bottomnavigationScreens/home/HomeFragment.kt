package com.example.graduation_project.ui.bottomnavigationScreens.home
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.data.remote.LoginRegiisterRetrofitInstance
import com.example.data.repo.repo.RepoImp
import com.example.domain.entity.Aya
import com.example.domain.usecase.AyaUseCase
import com.example.domain.usecase.SearchLexicalUseCase
import com.example.graduation_project.R
import com.example.graduation_project.databinding.FragmentHomeBinding
import com.example.graduation_project.databinding.ItemAyahBinding
import com.example.graduation_project.ui.base.BaseFragment
import com.example.graduation_project.ui.bottomnavigationScreens.Tafseer.TafseerViewModel
import com.example.graduation_project.ui.bottomnavigationScreens.Tafseer.TaseerViewModelFactory
import com.example.graduation_project.ui.bottomnavigationScreens.home.lexical.LexicalSearchFragment
import com.example.graduation_project.ui.bottomnavigationScreens.home.seimantic.SeimanticFragment


class HomeFragment : BaseFragment<FragmentHomeBinding>() {
    override val layoutFragmentId: Int
        get() = R.layout.fragment_home
    override val viewModel: ViewModel
        get() = TODO("Not yet implemented")

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.lexicalBtn.setOnClickListener {
            navigateToLexicalSearchFragment(LexicalSearchFragment())

        }

        binding.semanticBtn.setOnClickListener {
            navigateToLexicalSearchFragment(SeimanticFragment())
        }
    }

    private fun navigateToLexicalSearchFragment(fragment: Fragment) {
        parentFragmentManager.beginTransaction()
            .replace(R.id.frame_container, fragment)
            .addToBackStack(null)
            .commit()
    }
}


