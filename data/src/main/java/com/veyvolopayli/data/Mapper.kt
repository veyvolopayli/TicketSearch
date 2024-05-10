package com.veyvolopayli.data

import com.veyvolopayli.data.remote.model.musical_tickets_offers.MusicalTicketOfferDto
import com.veyvolopayli.data.remote.model.tickets.ArrivalDto
import com.veyvolopayli.data.remote.model.tickets.DepartureDto
import com.veyvolopayli.data.remote.model.tickets.HandLuggageDto
import com.veyvolopayli.data.remote.model.tickets.LuggageDto
import com.veyvolopayli.data.remote.model.tickets.PriceDto
import com.veyvolopayli.data.remote.model.tickets.TicketDto
import com.veyvolopayli.data.remote.model.tickets_offers.TicketOfferDto
import com.veyvolopayli.domain.model.musical_ticket_offer.MusicalTicketOffer
import com.veyvolopayli.domain.model.ticket.Arrival
import com.veyvolopayli.domain.model.ticket.Departure
import com.veyvolopayli.domain.model.ticket.HandLuggage
import com.veyvolopayli.domain.model.ticket.Luggage
import com.veyvolopayli.domain.model.ticket.Price
import com.veyvolopayli.domain.model.ticket.Ticket
import com.veyvolopayli.domain.model.ticket_offer.TicketOffer

fun TicketOffer.toTicketOfferDto(): TicketOfferDto {
    return TicketOfferDto(
        id = id,
        price = PriceDto(value = price.value),
        title = title,
        timeRange = timeRange
    )
}

fun TicketOfferDto.toTicketOffer(): TicketOffer {
    return TicketOffer(
        id = id,
        price = Price(value = price.value),
        title = title,
        timeRange = timeRange
    )
}

fun MusicalTicketOffer.toMusicalTicketOfferDto(): MusicalTicketOfferDto {
    return MusicalTicketOfferDto(
        id = id,
        price = PriceDto(value = price.value),
        title = title,
        town = town
    )
}

fun MusicalTicketOfferDto.toMusicalTicketOffer(): MusicalTicketOffer {
    return MusicalTicketOffer(
        id = id,
        price = Price(value = price.value),
        title = title,
        town = town
    )
}

fun Ticket.toTicketDto(): TicketDto {
    return TicketDto(
        id = id,
        badge = badge,
        price = PriceDto(value = price.value),
        providerName = providerName,
        company = company,
        departure = DepartureDto(
            airport = departure.airport,
            date = departure.date,
            town = departure.town,
        ),
        arrival = ArrivalDto(
            airport = arrival.airport,
            date = arrival.date,
            town = arrival.town,
        ),
        hasTransfer = hasTransfer,
        hasVisaTransfer = hasVisaTransfer,
        luggage = LuggageDto(
            hasLuggage = hasTransfer,
            price = PriceDto(
                value = price.value,
            )
        ),
        handLuggage = HandLuggageDto(
            hasHandLuggage = handLuggage.hasHandLuggage,
            size = handLuggage.size,
        ),
        isReturnable = isReturnable,
        isExchangeable = isExchangeable,
    )
}

fun TicketDto.toTicket(): Ticket {
    return Ticket(
        id = id,
        badge = badge,
        price = Price(value = price.value),
        providerName = providerName,
        company = company,
        departure = Departure(
            airport = departure.airport,
            date = departure.date,
            town = departure.town,
        ),
        arrival = Arrival(
            airport = arrival.airport,
            date = arrival.date,
            town = arrival.town,
        ),
        hasTransfer = hasTransfer,
        hasVisaTransfer = hasVisaTransfer,
        luggage = Luggage(
            hasLuggage = hasTransfer,
            price = Price(
                value = price.value,
            )
        ),
        handLuggage = HandLuggage(
            hasHandLuggage = handLuggage.hasHandLuggage,
            size = handLuggage.size,
        ),
        isReturnable = isReturnable,
        isExchangeable = isExchangeable,
    )
}