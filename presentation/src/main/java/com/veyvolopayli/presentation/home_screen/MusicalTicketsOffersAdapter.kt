package com.veyvolopayli.presentation.home_screen

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.veyvolopayli.domain.model.musical_ticket_offer.MusicalTicketOffer
import com.veyvolopayli.presentation.R
import com.veyvolopayli.presentation.common.roundOffImageCornersToDefault
import com.veyvolopayli.presentation.common.toUiPriceFrom
import com.veyvolopayli.presentation.databinding.ItemMusicalOfferBinding

class MusicalTicketsOffersAdapter : RecyclerView.Adapter<MusicalTicketsOffersAdapter.ViewHolder>() {

    private val offers = mutableListOf<MusicalTicketOffer>()

    class ViewHolder(binding: ItemMusicalOfferBinding): RecyclerView.ViewHolder(binding.root) {
        val image = binding.image
        val title = binding.title
        val town = binding.town
        val price = binding.price
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val binding = ItemMusicalOfferBinding.inflate(inflater)
        return ViewHolder(binding)
    }

    override fun getItemCount(): Int = offers.size

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val currentOffer = offers[position]
        val imageResourceId = when(currentOffer.id) {
            1 -> R.drawable.musical_offer_image_1
            2 -> R.drawable.musical_offer_image_2
            3 -> R.drawable.musical_offer_image_3
            else -> R.drawable.search_card_background
        }
        with(holder) {
            Glide.with(image).load(imageResourceId).into(image.roundOffImageCornersToDefault())
            title.text = currentOffer.title
            town.text = currentOffer.town
            price.text = currentOffer.price.value.toUiPriceFrom()
        }
    }

    fun setOffers(newOffers: List<MusicalTicketOffer>) {
        offers.apply {
            clear()
            addAll(newOffers)
        }
    }

}