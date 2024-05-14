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

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val parentView = view.parent as View
        BottomSheetBehavior.from(parentView).state = BottomSheetBehavior.STATE_EXPANDED
        view.minimumHeight = Resources.getSystem().displayMetrics.heightPixels

        val binding = FragmentSearchTicketsBinding.bind(view)
        this.binding = binding

        val startDestinationArg = arguments?.getString("START_DESTINATION")
        binding.startDestinationEt.setText(startDestinationArg)

        binding.suggestionsLayout.root.children.forEach {
            it.setOnClickListener { textView ->
                when(textView) {
                    binding.suggestionsLayout.anywhereTv -> {
                        binding.endDestinationEt.setText(resources.getString(R.string.anywhere))
                    }
                    else -> {
                        val bundle = bundleOf(PlugFragment.NAME_ARG to (textView as? TextView)?.text)
                        findNavController().navigate(
                            R.id.action_searchTicketsFragment_to_plugFragment, bundle, null
                        )
                    }
                }
            }
        }

        binding.startDestinationEt.addCyrillicTextWatcherFilter()
        binding.endDestinationEt.addCyrillicTextWatcherFilter()

        binding.popularSuggestionsLayout.itemIstanbul.setOnClickListener {
            binding.endDestinationEt.setText(R.string.istanbul)
        }

        binding.popularSuggestionsLayout.itemSochi.setOnClickListener {
            binding.endDestinationEt.setText(R.string.sochi)
        }

        binding.popularSuggestionsLayout.itemPhuket.setOnClickListener {
            binding.endDestinationEt.setText(R.string.phuket)
        }

        binding.endDestinationEt.onRightDrawableClick { it.text.clear() }

        binding.endDestinationEt.onDone {
            val bundle = bundleOf(
                "START_DESTINATION" to binding.startDestinationEt.text.toString().trim(),
                "END_DESTINATION" to binding.endDestinationEt.text.toString().trim()
            )
            findNavController().navigate(
                R.id.action_searchTicketsFragment_to_searchCountryChosenFragment, bundle
            )
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        binding = null
    }

}