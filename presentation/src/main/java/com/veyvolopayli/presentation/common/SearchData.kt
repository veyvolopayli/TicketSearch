package com.veyvolopayli.presentation.common

import java.io.Serializable
import java.time.LocalDate

data class SearchData(
    val departureLocation: String,
    val arrivalLocation: String,
    val departureDate: LocalDate,
    val passengersCount: Int = 1
): Serializable