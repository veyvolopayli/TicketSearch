package com.veyvolopayli.presentation.plug_screen

import android.os.Bundle
import android.view.View
import androidx.core.os.bundleOf
import androidx.fragment.app.Fragment
import com.veyvolopayli.presentation.R
import com.veyvolopayli.presentation.databinding.FragmentPlugBinding

class PlugFragment : Fragment(R.layout.fragment_plug) {

    private var binding: FragmentPlugBinding? = null

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val binding = FragmentPlugBinding.bind(view)
        this.binding = binding

        val name = arguments?.getString(NAME_ARG) ?: getString(R.string.no_name_fragment)
        binding.plugName.text = name
    }

    companion object {
        const val NAME_ARG = "plug_name"

        fun newInstance(fragmentName: String): PlugFragment {
            val bundle = bundleOf(NAME_ARG to fragmentName)
            return PlugFragment().also { it.arguments = bundle }
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        binding = null
    }

}