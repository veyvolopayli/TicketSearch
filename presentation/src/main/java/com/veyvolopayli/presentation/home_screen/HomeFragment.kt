package com.veyvolopayli.presentation.home_screen

import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.core.os.bundleOf
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.veyvolopayli.presentation.R
import com.veyvolopayli.presentation.common.UiState
import com.veyvolopayli.presentation.common.addCyrillicTextWatcherFilter
import com.veyvolopayli.presentation.databinding.FragmentHomeBinding
import org.koin.androidx.viewmodel.ext.android.viewModel

class HomeFragment : Fragment(R.layout.fragment_home) {

    private var binding: FragmentHomeBinding? = null
    private val vm: HomeViewModel by viewModel()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val binding = FragmentHomeBinding.bind(view)
        this.binding = binding

        val offersAdapter = MusicalTicketsOffersAdapter()
        binding.offersRv.setupRecyclerView(offersAdapter)

        vm.savedDepartureLocation.observe(viewLifecycleOwner) { savedLocation ->
            binding.startDestinationEt.apply {
                if (text.isNullOrEmpty()) {
                    setText(savedLocation)
                }
            }
        }

        vm.musicalOffersState.observe(viewLifecycleOwner) { state ->
            when (state) {
                is UiState.Success -> {
                    offersAdapter.setOffers(state.data)
                }
                is UiState.Error -> {
                    Toast.makeText(context, "Ошибка при загрузке данных", Toast.LENGTH_SHORT).show()
                }
                is UiState.Loading -> { }
            }
        }

        binding.endDestinationTv.setOnClickListener {
            val startDestinationText = binding.startDestinationEt.text.toString().trim()
            vm.saveDepartureLocation(startDestinationText)
            val action = HomeFragmentDirections
                .actionHomeFragmentToSearchTicketsFragment(startDestinationText)
            findNavController().navigate(action)
        }

        binding.startDestinationEt.addCyrillicTextWatcherFilter()
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