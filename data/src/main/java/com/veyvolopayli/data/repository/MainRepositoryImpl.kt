package com.veyvolopayli.data.repository

import com.veyvolopayli.data.remote.TicketsApi
import com.veyvolopayli.data.toMusicalTicketOffer
import com.veyvolopayli.data.toTicket
import com.veyvolopayli.data.toTicketOffer
import com.veyvolopayli.domain.model.musical_ticket_offer.MusicalTicketOffers
import com.veyvolopayli.domain.model.ticket.Tickets
import com.veyvolopayli.domain.model.ticket_offer.TicketsOffers
import com.veyvolopayli.domain.repository.MainRepository

class MainRepositoryImpl(private val ticketsApi: TicketsApi) : MainRepository {

    override suspend fun fetchMusicalTicketOffers(): MusicalTicketOffers {
        val musicalTicketsResponse = ticketsApi.fetchMusicalTicketOffers()
        return MusicalTicketOffers(musicalTicketsResponse.offers.map { it.toMusicalTicketOffer() })
    }

    override suspend fun fetchTicketOffers(): TicketsOffers {
        val ticketsOffersResponse = ticketsApi.fetchTicketOffers()
        return TicketsOffers(ticketsOffersResponse.ticketsOffers.map { it.toTicketOffer() })
    }

    override suspend fun fetchAllTickets(): Tickets {
        val allTicketsResponse = ticketsApi.fetchAllTickets()
        return Tickets(allTicketsResponse.tickets.map { it.toTicket() })
    }

}
