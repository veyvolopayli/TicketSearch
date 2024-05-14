package com.veyvolopayli.presentation.home_screen

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.veyvolopayli.common.RequestResult
import com.veyvolopayli.domain.model.musical_ticket_offer.MusicalTicketOffers
import com.veyvolopayli.domain.usecases.FetchMusicalTicketsOffersUseCase
import com.veyvolopayli.domain.usecases.GetDepartureLocationUseCase
import com.veyvolopayli.domain.usecases.SaveDepartureLocationUseCase
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.launch

class HomeViewModel(
    private val fetchMusicalTicketsOffersUseCase: FetchMusicalTicketsOffersUseCase,
    private val saveDepartureLocationUseCase: SaveDepartureLocationUseCase,
    private val getDepartureLocationUseCase: GetDepartureLocationUseCase,
) : ViewModel() {

    private val _musicalOffersState = MutableLiveData<MusicalTicketsOffersState>()
    val musicalOffersState: LiveData<MusicalTicketsOffersState> = _musicalOffersState

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