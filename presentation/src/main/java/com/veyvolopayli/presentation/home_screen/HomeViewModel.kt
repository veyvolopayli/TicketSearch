package com.veyvolopayli.presentation.home_screen

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.veyvolopayli.common.RequestResult
import com.veyvolopayli.domain.model.musical_ticket_offer.MusicalTicketOffers
import com.veyvolopayli.domain.usecases.FetchMusicalTicketsOffersUseCase
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach

class HomeViewModel(
    private val fetchMusicalTicketsOffersUseCase: FetchMusicalTicketsOffersUseCase,
) : ViewModel() {

    private val _musicalOffersState = MutableLiveData<MusicalTicketsOffersState>()
    val musicalOffersState: LiveData<MusicalTicketsOffersState> = _musicalOffersState

    init {
        fetchMusicalTicketsOffers()
    }

    fun fetchMusicalTicketsOffers() {
        fetchMusicalTicketsOffersUseCase().onEach { requestResult ->
            _musicalOffersState.value = requestResult.toState()
        }.launchIn(viewModelScope)
    }

}

private fun <T> RequestResult<T>.toState() : MusicalTicketsOffersState = when(this) {
    is RequestResult.Loading -> MusicalTicketsOffersState.Loading
    is RequestResult.Success -> MusicalTicketsOffersState.Success(data as MusicalTicketOffers)
    is RequestResult.Failure -> MusicalTicketsOffersState.Error()
}

sealed class MusicalTicketsOffersState {
    data object Loading : MusicalTicketsOffersState()
    class Success(val data: MusicalTicketOffers): MusicalTicketsOffersState()
    class Error(val data: MusicalTicketOffers? = null): MusicalTicketsOffersState()
}