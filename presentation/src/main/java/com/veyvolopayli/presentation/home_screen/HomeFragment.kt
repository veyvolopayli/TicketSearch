package com.veyvolopayli.presentation.home_screen

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import com.veyvolopayli.presentation.R
import com.veyvolopayli.presentation.databinding.FragmentHomeBinding
import org.koin.androidx.viewmodel.ext.android.viewModel

class HomeFragment : Fragment(R.layout.fragment_home) {

    private var binding: FragmentHomeBinding? = null
    private val vm: HomeViewModel by viewModel()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val binding = FragmentHomeBinding.bind(view)
        this.binding = binding

        vm.musicalOffersState.observe(viewLifecycleOwner) { state ->
            if (state is MusicalTicketsOffersState.Success) {
                println(state.data)
            }
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        binding = null
    }

}