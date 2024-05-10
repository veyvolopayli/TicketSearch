package com.veyvolopayli.data.remote.model.tickets_offers

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class TicketsOffersResponse(
    @SerialName("tickets_offers") val ticketsOffers: List<TicketOfferDto>
)