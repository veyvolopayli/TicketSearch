package com.veyvolopayli.presentation.main

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.commitNow
import androidx.navigation.fragment.NavHostFragment
import com.veyvolopayli.presentation.R
import com.veyvolopayli.presentation.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private var binding: ActivityMainBinding? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val binding = ActivityMainBinding.inflate(layoutInflater)
        this.binding = binding

        setContentView(binding.root)

        val navHostFragment = obtainNavHostFragment()
        supportFragmentManager.commitNow {
            replace(binding.mainFragmentContainer.id, navHostFragment)
            setPrimaryNavigationFragment(navHostFragment)
        }

    }

    private fun obtainNavHostFragment(): NavHostFragment {
        val existingNavHostFragment = binding?.mainFragmentContainer?.getFragment() as? NavHostFragment
        existingNavHostFragment?.let { return it }

        return NavHostFragment.create(R.navigation.main_nav_graph)
    }

}