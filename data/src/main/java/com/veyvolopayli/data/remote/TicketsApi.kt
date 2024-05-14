package com.veyvolopayli.data.remote

import com.veyvolopayli.data.remote.model.musical_tickets_offers.MusicalTicketsOffersResponse
import com.veyvolopayli.data.remote.model.tickets.TicketsResponse
import com.veyvolopayli.data.remote.model.tickets_offers.TicketsOffersResponse
import retrofit2.http.GET

interface TicketsApi {

    @GET("214a1713-bac0-4853-907c-a1dfc3cd05fd")
    suspend fun fetchMusicalTicketOffers(): MusicalTicketsOffersResponse

    @GET("7e55bf02-89ff-4847-9eb7-7d83ef884017")
    suspend fun fetchTicketOffers(): TicketsOffersResponse

    @GET("670c3d56-7f03-4237-9e34-d437a9e56ebf")
    suspend fun fetchAllTickets(): TicketsResponse

}