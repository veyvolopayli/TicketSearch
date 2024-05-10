package com.veyvolopayli.data.remote.model.tickets_offers

import com.veyvolopayli.data.remote.model.tickets.PriceDto
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class TicketOfferDto(
    @SerialName("id") val id: Int,
    @SerialName("price") val price: PriceDto,
    @SerialName("title") val title: String,
    @SerialName("time_range") val timeRange: List<String>
)