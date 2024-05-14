package com.veyvolopayli.presentation.all_tickets_screen

import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.LinearLayoutManager
import com.hannesdorfmann.adapterdelegates4.ListDelegationAdapter
import com.veyvolopayli.domain.model.ticket.Ticket
import com.veyvolopayli.presentation.R
import com.veyvolopayli.presentation.all_tickets_screen.adapter_delegates.TicketAdapterDelegate
import com.veyvolopayli.presentation.all_tickets_screen.adapter_delegates.TicketWithBadgeAdapterDelegate
import com.veyvolopayli.presentation.common.DiffUtilCallback
import com.veyvolopayli.presentation.common.UiState
import com.veyvolopayli.presentation.databinding.FragmentAllTicketsBinding
import org.koin.androidx.viewmodel.ext.android.viewModel
import java.time.format.DateTimeFormatter

class AllTicketsFragment : Fragment(R.layout.fragment_all_tickets) {

    private var binding: FragmentAllTicketsBinding? = null
    private val vm: AllTicketsViewModel by viewModel()
    private val navArgs: AllTicketsFragmentArgs by navArgs()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val binding = FragmentAllTicketsBinding.bind(view)
        this.binding = binding

        setTopPanelData()

        val ticketsAdapter = ListDelegationAdapter(
            TicketAdapterDelegate(),
            TicketWithBadgeAdapterDelegate()
        )

        binding.iconBack.setOnClickListener { findNavController().navigateUp() }

        binding.ticketsRv.apply {
            layoutManager = LinearLayoutManager(context)
            adapter = ticketsAdapter
        }

        vm.allTicketsState.observe(viewLifecycleOwner) { state ->
            when (state) {
                is UiState.Success -> {
                    ticketsAdapter.setupItems(state.data)
                }

                is UiState.Error -> {
                    Toast.makeText(context, "Ошибка загрузки данных", Toast.LENGTH_SHORT).show()
                }

                is UiState.Loading -> {}
            }
        }
    }

    private fun setTopPanelData() {
        val searchData = navArgs.searchData

        val formatter = DateTimeFormatter.ofPattern("dd MMMM")
        val formattedDepartureDate = formatter.format(searchData.departureDate)

        binding?.departureArrivalTv?.text = resources
            .getString(R.string.dash_two_items, searchData.departureLocation, searchData.arrivalLocation)

        binding?.datePassengerTv?.text = resources
            .getString(R.string.date_passenger, formattedDepartureDate, searchData.passengersCount)
    }

    private fun ListDelegationAdapter<List<Ticket>>.setupItems(newTickets: List<Ticket>) {
        val currentTickets = this.items ?: emptyList()

        val diffUtilCallback = DiffUtilCallback(
            oldList = currentTickets,
            newList = newTickets
        ) { oldItem, newItem -> oldItem.id == newItem.id }

        val diffResult = DiffUtil.calculateDiff(diffUtilCallback)

        this.items = newTickets
        diffResult.dispatchUpdatesTo(this)
    }

    override fun onDestroyView() {
        super.onDestroyView()
        binding = null
    }

}