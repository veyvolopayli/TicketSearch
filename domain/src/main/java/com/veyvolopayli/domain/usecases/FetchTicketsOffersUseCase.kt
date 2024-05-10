package com.veyvolopayli.domain.usecases

import com.veyvolopayli.common.RequestResult
import com.veyvolopayli.domain.repository.MainRepository
import kotlinx.coroutines.flow.flow

class FetchTicketsOffersUseCase(private val mainRepository: MainRepository) {
    operator fun invoke() = flow {
        try {
            emit(RequestResult.Loading())
            val tickets = mainRepository.fetchTicketOffers()
            emit(RequestResult.Success(tickets))
        } catch (e: Exception) {
            e.printStackTrace()
            emit(RequestResult.Failure(error = e))
        }
    }
}