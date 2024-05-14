package com.veyvolopayli.presentation.search_tickets_screen

import android.content.res.Resources
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.view.View
import android.widget.TextView
import androidx.core.os.bundleOf
import androidx.core.view.children
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.google.android.material.bottomsheet.BottomSheetBehavior
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import com.veyvolopayli.presentation.R
import com.veyvolopayli.presentation.common.addCyrillicTextWatcherFilter
import com.veyvolopayli.presentation.common.onDone
import com.veyvolopayli.presentation.plug_screen.PlugFragment
import com.veyvolopayli.presentation.common.onRightDrawableClick
import com.veyvolopayli.presentation.databinding.FragmentSearchTicketsBinding


class SearchTicketsDialogFragment : BottomSheetDialogFragment(R.layout.fragment_search_tickets) {

    private var binding: FragmentSearchTicketsBinding? = null
    private val args: SearchTicketsDialogFragmentArgs by navArgs()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val parentView = view.parent as View
        BottomSheetBehavior.from(parentView).state = BottomSheetBehavior.STATE_EXPANDED
        view.minimumHeight = Resources.getSystem().displayMetrics.heightPixels

        val binding = FragmentSearchTicketsBinding.bind(view)
        this.binding = binding

        binding.startDestinationEt.setText(args.departureLocation)

        binding.suggestionsLayout.root.children.forEach {
            it.setOnClickListener { textView ->
                when (textView) {
                    binding.suggestionsLayout.anywhereTv -> {
                        binding.endDestinationEt.setText(resources.getString(R.string.anywhere))
                        navigateToCountryChosenSearch()
                    }

                    else -> {
                        val action = SearchTicketsDialogFragmentDirections
                            .actionSearchTicketsFragmentToPlugFragment(
                                (textView as? TextView)?.text.toString()
                            )

                        findNavController().navigate(action)
                    }
                }
            }
        }

        binding.startDestinationEt.addCyrillicTextWatcherFilter()
        binding.endDestinationEt.addCyrillicTextWatcherFilter()

        binding.popularSuggestionsLayout.itemIstanbul.setOnClickListener {
            binding.endDestinationEt.setText(R.string.istanbul)
            navigateToCountryChosenSearch()
        }

        binding.popularSuggestionsLayout.itemSochi.setOnClickListener {
            binding.endDestinationEt.setText(R.string.sochi)
            navigateToCountryChosenSearch()
        }

        binding.popularSuggestionsLayout.itemPhuket.setOnClickListener {
            binding.endDestinationEt.setText(R.string.phuket)
            navigateToCountryChosenSearch()
        }

        binding.endDestinationEt.onRightDrawableClick { it.text.clear() }

        binding.endDestinationEt.onDone {
            navigateToCountryChosenSearch()
        }
    }

    private fun navigateToCountryChosenSearch() {
        val action = SearchTicketsDialogFragmentDirections
            .actionSearchTicketsFragmentToSearchCountryChosenFragment(
                departureLocation = binding?.startDestinationEt?.text.toString().trim(),
                arrivalLocation = binding?.endDestinationEt?.text.toString().trim()
            )
        findNavController().navigate(action)
    }

    override fun onDestroyView() {
        super.onDestroyView()
        binding = null
    }

}