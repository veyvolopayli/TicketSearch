package com.veyvolopayli.ticketsearch.di

import com.veyvolopayli.domain.usecases.FetchAllTicketsUseCase
import com.veyvolopayli.domain.usecases.FetchMusicalTicketsOffersUseCase
import com.veyvolopayli.domain.usecases.FetchTicketsOffersUseCase
import com.veyvolopayli.domain.usecases.GetDepartureLocationUseCase
import com.veyvolopayli.domain.usecases.SaveDepartureLocationUseCase
import org.koin.dsl.module

val domainModule = module {

    factory<FetchAllTicketsUseCase> {
        FetchAllTicketsUseCase(mainRepository = get())
    }

    factory {
        FetchMusicalTicketsOffersUseCase(mainRepository = get())
    }

    factory {
        FetchTicketsOffersUseCase(mainRepository = get())
    }

    factory {
        SaveDepartureLocationUseCase(storageRepository = get())
    }

    factory {
        GetDepartureLocationUseCase(storageRepository = get())
    }

}