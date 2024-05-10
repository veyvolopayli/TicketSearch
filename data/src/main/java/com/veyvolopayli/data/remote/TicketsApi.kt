package com.veyvolopayli.data.remote

import com.veyvolopayli.data.remote.model.musical_tickets_offers.MusicalTicketsOffersResponse
import com.veyvolopayli.data.remote.model.tickets.TicketsResponse
import com.veyvolopayli.data.remote.model.tickets_offers.TicketsOffersResponse
import retrofit2.http.GET

interface TicketsApi {

    @GET("10fbb71b-35d4-4290-9ae8-f787bc43025e")
    suspend fun fetchMusicalTicketOffers(): MusicalTicketsOffersResponse

    @GET("7199afdb-b45d-4e87-b7a9-58792ecb1390")
    suspend fun fetchTicketOffers(): TicketsOffersResponse

    @GET("592cb26e-11dc-4c16-9058-8868b7a422b8")
    suspend fun fetchAllTickets(): TicketsResponse

}