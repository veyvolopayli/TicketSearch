package com.veyvolopayli.data.remote.model.tickets

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class TicketsResponse(
    @SerialName("tickets") val tickets: List<TicketDto>
)
