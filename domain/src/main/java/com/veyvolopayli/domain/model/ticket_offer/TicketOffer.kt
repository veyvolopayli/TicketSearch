package com.veyvolopayli.domain.model.ticket_offer

import com.veyvolopayli.domain.model.ticket.Price

data class TicketOffer(
    val id: Int,
    val price: Price,
    val timeRange: List<String>,
    val title: String
)