package com.veyvolopayli.ticketsearch

import com.veyvolopayli.domain.usecases.FetchAllTicketsUseCase
import com.veyvolopayli.domain.usecases.FetchMusicalTicketsOffersUseCase
import com.veyvolopayli.domain.usecases.FetchTicketsOffersUseCase
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

}