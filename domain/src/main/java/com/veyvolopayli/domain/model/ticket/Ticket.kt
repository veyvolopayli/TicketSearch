package com.veyvolopayli.domain.model.ticket

import java.time.LocalDateTime

data class Arrival(
    val airport: String,
    val date: LocalDateTime,
    val town: String
)

data class Departure(
    val airport: String,
    val date: LocalDateTime,
    val town: String
)

data class HandLuggage(
    val hasHandLuggage: Boolean,
    val size: String? = null,
)

data class Luggage(
    val hasLuggage: Boolean,
    val price: Price? = null,
)

data class Price(
    val value: Int,
)

data class Ticket(
    val id: Int,
    val badge: String? = null,
    val price: Price,
    val providerName: String,
    val company: String,
    val departure: Departure,
    val arrival: Arrival,
    val hasTransfer: Boolean,
    val hasVisaTransfer: Boolean,
    val luggage: Luggage,
    val handLuggage: HandLuggage,
    val isReturnable: Boolean,
    val isExchangeable: Boolean,
)