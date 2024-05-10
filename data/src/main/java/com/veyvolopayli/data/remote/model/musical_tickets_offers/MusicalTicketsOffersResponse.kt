package com.veyvolopayli.data.remote.model.musical_tickets_offers

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class MusicalTicketsOffersResponse(
    @SerialName("offers") val offers: List<MusicalTicketOfferDto>
)