package com.veyvolopayli.presentation.main

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.fragment.app.commitNow
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.setupWithNavController
import com.veyvolopayli.presentation.R
import com.veyvolopayli.presentation.databinding.FragmentMainBinding

class MainFragment : Fragment(R.layout.fragment_main) {

    private var binding: FragmentMainBinding? = null

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val binding = FragmentMainBinding.bind(view)
        this.binding = binding

        val navHostFragment = obtainNavHostFragment()

        childFragmentManager.commitNow {
            replace(binding.fragmentContainer.id, navHostFragment)
            setPrimaryNavigationFragment(navHostFragment)
        }

        binding.bottomNavigation.setupWithNavController(navHostFragment.navController)
    }

    private fun obtainNavHostFragment(): NavHostFragment {
        val existingNavHostFragment = binding?.fragmentContainer?.getFragment() as? NavHostFragment
        existingNavHostFragment?.let { return it }

        return NavHostFragment.create(R.navigation.tabs_graph)
    }

    override fun onDestroyView() {
        super.onDestroyView()
        binding = null
    }

}