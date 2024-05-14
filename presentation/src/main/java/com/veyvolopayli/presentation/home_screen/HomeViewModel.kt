package com.veyvolopayli.presentation.home_screen

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.veyvolopayli.common.RequestResult
import com.veyvolopayli.domain.model.musical_ticket_offer.MusicalTicketOffer
import com.veyvolopayli.domain.model.musical_ticket_offer.MusicalTicketOffers
import com.veyvolopayli.domain.usecases.FetchMusicalTicketsOffersUseCase
import com.veyvolopayli.domain.usecases.GetDepartureLocationUseCase
import com.veyvolopayli.domain.usecases.SaveDepartureLocationUseCase
import com.veyvolopayli.presentation.common.UiState
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.launch

class HomeViewModel(
    private val fetchMusicalTicketsOffersUseCase: FetchMusicalTicketsOffersUseCase,
    private val saveDepartureLocationUseCase: SaveDepartureLocationUseCase,
    private val getDepartureLocationUseCase: GetDepartureLocationUseCase,
) : ViewModel() {

    private val _musicalOffersState = MutableLiveData<UiState<List<MusicalTicketOffer>>>()
    val musicalOffersState: LiveData<UiState<List<MusicalTicketOffer>>> = _musicalOffersState

    private val _savedDepartureLocation = MutableLiveData<String>()
    val savedDepartureLocation: LiveData<String> = _savedDepartureLocation

    init {
        fetchMusicalTicketsOffers()
        getDepartureLocation()
    }

    fun saveDepartureLocation(location: String) {
        viewModelScope.launch { saveDepartureLocationUseCase(location) }
    }

    private fun getDepartureLocation() {
        viewModelScope.launch {
            val location = getDepartureLocationUseCase() ?: return@launch
            _savedDepartureLocation.value = location
        }
    }

    fun fetchMusicalTicketsOffers() {
        fetchMusicalTicketsOffersUseCase().onEach { requestResult ->
            _musicalOffersState.value = requestResult.toUiState()
        }.launchIn(viewModelScope)
    }

}

private fun RequestResult<MusicalTicketOffers>.toUiState() : UiState<List<MusicalTicketOffer>> {
    return when(this) {
        is RequestResult.Loading -> UiState.Loading()
        is RequestResult.Success -> UiState.Success(data.musicalTicketOffers)
        is RequestResult.Failure -> UiState.Error(
            data = data?.musicalTicketOffers, errorMessage = error.message ?: "Произошла ошибка"
        )
    }
}