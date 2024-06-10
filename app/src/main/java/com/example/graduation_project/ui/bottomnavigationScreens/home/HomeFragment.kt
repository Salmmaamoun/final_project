package com.example.graduation_project.ui.bottomnavigationScreens.home
import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModel
import com.example.graduation_project.R
import com.example.graduation_project.databinding.FragmentHomeBinding
import com.example.graduation_project.ui.base.BaseFragment
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