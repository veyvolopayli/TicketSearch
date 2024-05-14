package com.veyvolopayli.presentation.search_country_chosen_screen

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.veyvolopayli.common.RequestResult
import com.veyvolopayli.domain.model.ticket_offer.TicketOffer
import com.veyvolopayli.domain.model.ticket_offer.TicketsOffers
import com.veyvolopayli.domain.usecases.FetchTicketsOffersUseCase
import com.veyvolopayli.presentation.R
import com.veyvolopayli.presentation.common.UiState
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import java.time.LocalDate

class SearchCountryChosenViewModel(
    private val fetchTicketsOffersUseCase: FetchTicketsOffersUseCase
) : ViewModel() {

    private val _topMenuState = MutableLiveData<TopMenuState>(TopMenuState())
    val topMenuState: LiveData<TopMenuState> = _topMenuState

    private val _destinationsInputState = MutableLiveData<InputsState>()
    val destinationsInputState: LiveData<InputsState> = _destinationsInputState

    private val _ticketsOffersState = MutableLiveData<UiState<List<TicketOffer>>>()
    val ticketsOffersState: LiveData<UiState<List<TicketOffer>>> = _ticketsOffersState

    init {
        fetchTicketsOffers()
    }

    private fun fetchTicketsOffers() {
        fetchTicketsOffersUseCase().onEach { requestResult ->
            _ticketsOffersState.value = requestResult.toUiState()
        }.launchIn(viewModelScope)
    }

    fun setDepartureDate(date: LocalDate) {
        _topMenuState.value = _topMenuState.value?.copy(
            departureDate = date
        ) ?: return
    }

    fun setReturnTicketDate(date: LocalDate) {
        _topMenuState.value = _topMenuState.value?.copy(
            returnTicketDate = date
        ) ?: return
    }

    fun swapCities() {
        val currentState = _destinationsInputState.value ?: return
        _destinationsInputState.value = InputsState(
            startCity = currentState.targetCity,
            targetCity = currentState.startCity,
        )
    }
}

private fun RequestResult<TicketsOffers>.toUiState() : UiState<List<TicketOffer>> {
    return when(this) {
        is RequestResult.Loading -> UiState.Loading()
        is RequestResult.Success -> UiState.Success(this.data.ticketsOffers)
        is RequestResult.Failure -> UiState.Error(
            data = this.data?.ticketsOffers, errorMessage = this.error.message ?: ""
        )
    }
}

data class InputsState(
    val startCity: String,
    val targetCity: String,
)

/**
 * @param passengersClass содержит id строкового ресурса
 */
data class TopMenuState(
    val departureDate: LocalDate = LocalDate.now(),
    val returnTicketDate: LocalDate? = null,
    val passengersCount: Int = 1,
    val passengersClass: Int = R.string.econom,
)