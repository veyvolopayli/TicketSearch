package com.veyvolopayli.data.remote.model.tickets

import com.veyvolopayli.data.LocalDateTimeSerializer
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable
import java.time.LocalDateTime

@Serializable
data class ArrivalDto(
    @SerialName("airport") val airport: String,
    @SerialName("date") val date: String,
    @SerialName("town") val town: String
)

@Serializable
data class DepartureDto(
    @SerialName("airport") val airport: String,
    @Serializable(with = LocalDateTimeSerializer::class)
    @SerialName("date") val date: LocalDateTime,
    @SerialName("town") val town: String
)

@Serializable
data class HandLuggageDto(
    @SerialName("has_hand_luggage") val hasHandLuggage: Boolean,
    @SerialName("size") val size: String? = null,
)

@Serializable
data class LuggageDto(
    @SerialName("has_luggage") val hasLuggage: Boolean,
    @SerialName("price") val price: PriceDto? = null,
)

@Serializable
data class PriceDto(
    @SerialName("value") val value: Int,
)

@Serializable
data class TicketDto(
    @SerialName("id") val id: Int,
    @SerialName("badge") val badge: String? = null,
    @SerialName("price") val price: PriceDto,
    @SerialName("provider_name") val providerName: String,
    @SerialName("company") val company: String,
    @SerialName("departure") val departure: DepartureDto,
    @SerialName("arrival") val arrival: ArrivalDto,
    @SerialName("has_transfer") val hasTransfer: Boolean,
    @SerialName("has_visa_transfer") val hasVisaTransfer: Boolean,
    @SerialName("luggage") val luggage: LuggageDto,
    @SerialName("hand_luggage") val handLuggage: HandLuggageDto,
    @SerialName("is_returnable") val isReturnable: Boolean,
    @SerialName("is_exchangable") val isExchangeable: Boolean,
)