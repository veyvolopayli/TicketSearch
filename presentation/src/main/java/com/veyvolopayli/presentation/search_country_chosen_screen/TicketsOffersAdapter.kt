package com.veyvolopayli.presentation.search_country_chosen_screen

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.veyvolopayli.domain.model.ticket_offer.TicketOffer
import com.veyvolopayli.presentation.R
import com.veyvolopayli.presentation.common.DiffUtilCallback
import com.veyvolopayli.presentation.common.toUiPrice
import com.veyvolopayli.presentation.databinding.ItemDirectFlightTicketBinding

class TicketsOffersAdapter : RecyclerView.Adapter<TicketsOffersAdapter.ViewHolder>() {
    private var offers = emptyList<TicketOffer>()

    class ViewHolder(binding: ItemDirectFlightTicketBinding): RecyclerView.ViewHolder(binding.root) {
        val circleView = binding.circleView
        val titleTv = binding.titleTv
        val priceTv = binding.priceTv
        val timeTv = binding.timeTv
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val binding = ItemDirectFlightTicketBinding.inflate(inflater)
        return ViewHolder(binding)
    }

    fun setItems(newOffers: List<TicketOffer>) {
        val diffCallback = DiffUtilCallback(offers, newOffers) { oldItem, newItem ->
            oldItem.id == newItem.id
        }
        val diffResult = DiffUtil.calculateDiff(diffCallback)
        offers = newOffers
        diffResult.dispatchUpdatesTo(this)
    }

    override fun getItemCount(): Int = if (offers.size > 3) 3 else offers.size

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val currentItem = offers[position]
        val context = holder.circleView.context
        val circleColor = circleColors[position % circleColors.size]

        with(holder) {
            circleView.backgroundTintList = context.getColorStateList(circleColor)
            titleTv.text = currentItem.title
            priceTv.text = currentItem.price.value.toUiPrice()
            timeTv.text = currentItem.timeRange.joinToString(" ")
        }
    }

    companion object {
        private val circleColors = listOf(R.color.red, R.color.blue, R.color.white)
    }
}