package com.veyvolopayli.domain.repository

import com.veyvolopayli.domain.model.musical_ticket_offer.MusicalTicketOffers
import com.veyvolopayli.domain.model.ticket.Tickets
import com.veyvolopayli.domain.model.ticket_offer.TicketsOffers

interface MainRepository {
    suspend fun fetchMusicalTicketOffers(): MusicalTicketOffers
    suspend fun fetchTicketOffers(): TicketsOffers
    suspend fun fetchAllTickets(): Tickets
}