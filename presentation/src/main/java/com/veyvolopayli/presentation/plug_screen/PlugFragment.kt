package com.veyvolopayli.presentation.plug_screen

import android.os.Bundle
import android.view.View
import androidx.core.os.bundleOf
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.navArgs
import com.veyvolopayli.presentation.R
import com.veyvolopayli.presentation.databinding.FragmentPlugBinding

class PlugFragment : Fragment(R.layout.fragment_plug) {

    private var binding: FragmentPlugBinding? = null
    private val args: PlugFragmentArgs by navArgs()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val binding = FragmentPlugBinding.bind(view)
        this.binding = binding

        binding.plugName.text = args.plugName
    }

    override fun onDestroyView() {
        super.onDestroyView()
        binding = null
    }

}