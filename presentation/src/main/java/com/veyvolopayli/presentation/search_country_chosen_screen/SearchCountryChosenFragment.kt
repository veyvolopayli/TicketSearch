package com.veyvolopayli.presentation.search_country_chosen_screen

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import com.veyvolopayli.presentation.R
import com.veyvolopayli.presentation.databinding.FragmentSearchCountryChosenBinding
import org.koin.androidx.viewmodel.ext.android.viewModel

class SearchCountryChosenFragment() : Fragment(R.layout.fragment_search_country_chosen) {

    private var binding: FragmentSearchCountryChosenBinding? = null
    private val viewModel: SearchCountryChosenViewModel by viewModel()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val binding = FragmentSearchCountryChosenBinding.bind(view)
        this.binding = binding


    }

    override fun onDestroyView() {
        super.onDestroyView()
        binding = null
    }

}