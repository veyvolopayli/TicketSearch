package com.veyvolopayli.ticketsearch.di

import com.veyvolopayli.presentation.all_tickets_screen.AllTicketsViewModel
import com.veyvolopayli.presentation.home_screen.HomeViewModel
import com.veyvolopayli.presentation.search_country_chosen_screen.SearchCountryChosenViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val presentationModule = module {

    viewModel {
        HomeViewModel(
            fetchMusicalTicketsOffersUseCase = get(),
            saveDepartureLocationUseCase = get(),
            getDepartureLocationUseCase = get()
        )
    }

    viewModel {
        SearchCountryChosenViewModel(fetchTicketsOffersUseCase = get())
    }

    viewModel {
        AllTicketsViewModel(fetchAllTicketsUseCase = get())
    }

}