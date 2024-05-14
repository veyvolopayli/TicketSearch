package com.veyvolopayli.presentation.search_country_chosen_screen

import android.app.DatePickerDialog
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import androidx.recyclerview.widget.LinearLayoutManager
import com.veyvolopayli.presentation.R
import com.veyvolopayli.presentation.common.SearchData
import com.veyvolopayli.presentation.common.UiState
import com.veyvolopayli.presentation.common.formatShort
import com.veyvolopayli.presentation.common.onRightDrawableClick
import com.veyvolopayli.presentation.databinding.FragmentSearchCountryChosenBinding
import org.koin.androidx.viewmodel.ext.android.viewModel
import java.time.LocalDate
import java.time.ZoneId
import java.util.Calendar
import java.util.Date

class SearchCountryChosenFragment() : Fragment(R.layout.fragment_search_country_chosen) {

    private var binding: FragmentSearchCountryChosenBinding? = null
    private val viewModel: SearchCountryChosenViewModel by viewModel()
    private val returningCalendar: Calendar = Calendar.getInstance()
    private val departureCalendar: Calendar = Calendar.getInstance()
    private val args: SearchCountryChosenFragmentArgs by navArgs()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val binding = FragmentSearchCountryChosenBinding.bind(view)
        this.binding = binding

        with(binding) {
            startDestinationEt.setText(args.departureLocation)
            endDestinationEt.setText(args.arrivalLocation)
            returnTicketDateTv.setOnClickListener {
                returningCalendar.showDatePicker(CalendarType.FROM)
            }
            departureDateTv.setOnClickListener {
                departureCalendar.showDatePicker(CalendarType.TO)
            }
            startDestinationEt.onRightDrawableClick { swapDestinations() }
            endDestinationEt.onRightDrawableClick { it.text.clear() }
            seeAllTicketsBtn.setOnClickListener {
                val searchData = SearchData(
                    departureLocation = binding.startDestinationEt.text.toString().trim(),
                    arrivalLocation = binding.endDestinationEt.text.toString().trim(),
                    departureDate = viewModel.topMenuState.value?.departureDate ?: LocalDate.now(),
                )
                val action = SearchCountryChosenFragmentDirections
                    .actionSearchCountryChosenFragmentToAllTicketsFragment(searchData)
                findNavController().navigate(action)
            }
            iconBack.setOnClickListener { findNavController().navigateUp() }
        }

        val offersAdapter = TicketsOffersAdapter()
        binding.offersRv.apply {
            layoutManager = LinearLayoutManager(context)
            adapter = offersAdapter
        }

        viewModel.topMenuState.observe(viewLifecycleOwner) { menuState ->
            val passengerClass = resources.getString(menuState.passengersClass)
            binding.departureDateTv.text = menuState.departureDate.formatShort()
            departureCalendar.time = Date.from(menuState.departureDate.atStartOfDay(ZoneId.systemDefault()).toInstant())
            binding.passengersClassTv.text = resources.getString(
                R.string.passenger_count_and_class, menuState.passengersCount, passengerClass
            )
            menuState.returnTicketDate?.let { date ->
                binding.returnTicketDateTv.text = date.formatShort()
                returningCalendar.time = Date.from(date.atStartOfDay(ZoneId.systemDefault()).toInstant())
            }
        }

        viewModel.ticketsOffersState.observe(viewLifecycleOwner) { state ->
            when (state) {
                is UiState.Success -> {
                    offersAdapter.setItems(state.data)
                }

                is UiState.Error -> {
                    Toast.makeText(
                        context,
                        getString(R.string.error_occurred_on_data_loading),
                        Toast.LENGTH_SHORT
                    ).show()
                }

                is UiState.Loading -> {
                    // Loading logic
                }
            }
        }
    }

    private fun swapDestinations() {
        val startDestinationEt = binding?.startDestinationEt ?: return
        val endDestinationEt = binding?.endDestinationEt ?: return

        val currentStart = startDestinationEt.text.toString()
        val currentEnd = endDestinationEt.text.toString()

        startDestinationEt.setText(currentEnd)
        endDestinationEt.setText(currentStart)
    }

    enum class CalendarType {
        TO, FROM
    }

    private fun Calendar.showDatePicker(calendarType: CalendarType) {
        val datePickerDialog = DatePickerDialog(
            requireContext(),
            { _, year, month, dayOfMonth ->
                this.set(Calendar.YEAR, year)
                this.set(Calendar.MONTH, month)
                this.set(Calendar.DAY_OF_MONTH, dayOfMonth)
                val localDate = this.time.toInstant().atZone(ZoneId.systemDefault()).toLocalDate()
                when(calendarType) {
                    CalendarType.TO -> viewModel.setDepartureDate(localDate)
                    CalendarType.FROM -> viewModel.setReturnTicketDate(localDate)
                }
            },
            this.get(Calendar.YEAR),
            this.get(Calendar.MONTH),
            this.get(Calendar.DAY_OF_MONTH)
        )
        datePickerDialog.show()
    }

    override fun onDestroyView() {
        super.onDestroyView()
        binding = null
    }

}