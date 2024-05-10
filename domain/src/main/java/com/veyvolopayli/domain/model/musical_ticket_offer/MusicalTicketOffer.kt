package com.veyvolopayli.domain.model.musical_ticket_offer

import com.veyvolopayli.domain.model.ticket.Price

data class MusicalTicketOffer(
    val id: Int,
    val price: Price,
    val title: String,
    val town: String
)