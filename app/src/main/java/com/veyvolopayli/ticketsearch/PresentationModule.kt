package com.veyvolopayli.ticketsearch

import com.veyvolopayli.presentation.home_screen.HomeViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val presentationModule = module {

    viewModel {
        HomeViewModel(fetchMusicalTicketsOffersUseCase = get())
    }

}