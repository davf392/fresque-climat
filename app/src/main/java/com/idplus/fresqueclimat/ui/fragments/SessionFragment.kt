package com.idplus.fresqueclimat.ui.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.activityViewModels
import androidx.fragment.app.viewModels
import androidx.lifecycle.ViewModelProvider
import com.idplus.fresqueclimat.ui.adapter.SessionItemAdapter
import com.idplus.fresqueclimat.databinding.FragmentSessionBinding
import com.idplus.fresqueclimat.ui.viewmodel.ResourceViewModel
import com.idplus.fresqueclimat.ui.viewmodel.SessionViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class SessionFragment : Fragment() {

    private lateinit var binding: FragmentSessionBinding
    private val sessionViewModel by viewModels<SessionViewModel>()

    override fun onCreateView(inf: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        val fragmentBinding = FragmentSessionBinding.inflate(inf, container, false)
        binding = fragmentBinding
        return fragmentBinding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.viewModel = sessionViewModel
        binding.lifecycleOwner = viewLifecycleOwner

        // add the adapter to the sessionsList recycler view
        val adapter = SessionItemAdapter()
        binding.sessionsList.adapter = adapter

        sessionViewModel.sessions.observe(viewLifecycleOwner) {
            it?.let {
                adapter.data = it
            }
        }
    }

}