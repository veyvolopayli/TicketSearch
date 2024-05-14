package com.veyvolopayli.presentation.all_tickets_screen.adapter_delegates

import android.util.TypedValue
import android.view.LayoutInflater
import android.view.ViewGroup
import android.view.ViewGroup.LayoutParams.MATCH_PARENT
import android.view.ViewGroup.LayoutParams.WRAP_CONTENT
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.core.view.marginBottom
import androidx.recyclerview.widget.RecyclerView
import com.hannesdorfmann.adapterdelegates4.AdapterDelegate
import com.veyvolopayli.domain.model.ticket.Ticket
import com.veyvolopayli.presentation.common.buildTravelTimeString
import com.veyvolopayli.presentation.common.toUiPrice
import com.veyvolopayli.presentation.databinding.ItemTicketBinding


class TicketAdapterDelegate : AdapterDelegate<List<Ticket>>() {
    override fun isForViewType(items: List<Ticket>, position: Int): Boolean {
        return items[position].badge == null
    }

    override fun onCreateViewHolder(parent: ViewGroup): RecyclerView.ViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val marginBottomInPx = TypedValue.applyDimension(
            TypedValue.COMPLEX_UNIT_DIP,
            16.toFloat(),
            parent.context.resources.displayMetrics
        ).toInt()
        val layoutParams = ConstraintLayout.LayoutParams(MATCH_PARENT, WRAP_CONTENT).apply {
            setMargins(0, 0, 0, marginBottomInPx)
        }
        val binding = ItemTicketBinding.inflate(inflater).also {
            it.root.layoutParams = layoutParams
        }
        return TicketViewHolder(binding)
    }

    override fun onBindViewHolder(
        items: List<Ticket>,
        position: Int,
        holder: RecyclerView.ViewHolder,
        payloads: MutableList<Any>
    ) {
        val ticket = items[position]
        (holder as TicketViewHolder).bind(ticket)
    }

    class TicketViewHolder(
        private val binding: ItemTicketBinding
    ) : RecyclerView.ViewHolder(binding.root) {
        fun bind(ticket: Ticket) {
            binding.apply {
                priceTv.text = ticket.price.value.toUiPrice()
                departureTimeTv.text = ticket.departure.date.toLocalTime().toString()
                departureAirportTv.text = ticket.departure.airport
                arrivalTimeTv.text = ticket.arrival.date.toLocalTime().toString()
                arrivalAirportTv.text = ticket.arrival.airport
                travelTimeTv.text = buildTravelTimeString(ticket.departure, ticket.arrival, ticket.hasTransfer)
            }
        }
    }

}