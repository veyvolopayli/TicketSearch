package com.veyvolopayli.presentation.home_screen

import android.os.Bundle
import android.view.View
import androidx.core.os.bundleOf
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
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
            when(state) {
                is MusicalTicketsOffersState.Loading -> {

                }
                is MusicalTicketsOffersState.Success -> {
                    val offersAdapter = MusicalTicketsOffersAdapter().also {
                        it.setOffers(state.data.musicalTicketOffers)
                    }
                    binding.offersRv.setupRecyclerView(offersAdapter)
                }
                is MusicalTicketsOffersState.Error -> {

                }
            }
        }

        binding.endDestinationTv.setOnClickListener {
            val startDestinationText = binding.startDestinationEt.text?.toString()
            val argBundle = bundleOf("START_DESTINATION" to startDestinationText)
            findNavController().navigate(R.id.action_homeFragment_to_searchTicketsFragment, argBundle)
        }
    }

    private fun RecyclerView.setupRecyclerView(offersAdapter: MusicalTicketsOffersAdapter) {
        layoutManager = LinearLayoutManager(
            context, LinearLayoutManager.HORIZONTAL, false
        )
        addItemDecoration(
            MusicalTicketOfferDecorator(
            resources.getDimensionPixelSize(R.dimen.musical_offers_items_space)
        )
        )
        adapter = offersAdapter
    }

    override fun onDestroyView() {
        super.onDestroyView()
        binding = null
    }

}