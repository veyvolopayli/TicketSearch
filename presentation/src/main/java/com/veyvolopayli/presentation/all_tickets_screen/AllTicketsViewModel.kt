package com.veyvolopayli.presentation.all_tickets_screen

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.veyvolopayli.common.RequestResult
import com.veyvolopayli.domain.model.ticket.Ticket
import com.veyvolopayli.domain.model.ticket.Tickets
import com.veyvolopayli.domain.usecases.FetchAllTicketsUseCase
import com.veyvolopayli.presentation.common.UiState
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach

class AllTicketsViewModel(
    private val fetchAllTicketsUseCase: FetchAllTicketsUseCase
) : ViewModel() {

    private val _allTicketsState = MutableLiveData<UiState<List<Ticket>>>()
    val allTicketsState: LiveData<UiState<List<Ticket>>> = _allTicketsState

    init {
        fetchAllTickets()
    }

    private fun fetchAllTickets() {
        fetchAllTicketsUseCase().onEach { requestResult ->
            _allTicketsState.value = requestResult.toUiState()
        }.launchIn(viewModelScope)
    }

    private fun RequestResult<Tickets>.toUiState(): UiState<List<Ticket>> = when (this) {
        is RequestResult.Loading -> UiState.Loading()
        is RequestResult.Success -> UiState.Success(data.tickets)
        is RequestResult.Failure -> UiState.Error(
            data?.tickets, error.message ?: "Ошибка загрузки данных"
        )
    }
}

