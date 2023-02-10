package com.idplus.fresqueclimat.ui.session_details

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.navigation.NavController
import androidx.navigation.fragment.findNavController
import com.idplus.fresqueclimat.databinding.FragmentSessionDetailsBinding
import com.idplus.fresqueclimat.ui.sessions.SessionItem

class SessionDetailsFragment : Fragment() {

    private lateinit var binding: FragmentSessionDetailsBinding
    private lateinit var navController: NavController
    private val viewModel by viewModels<SessionDetailsViewModel>()

    override fun onCreateView(inf: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val fragmentBinding = FragmentSessionDetailsBinding.inflate(inf, container, false)
        binding = fragmentBinding
        return fragmentBinding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val sessionItem: SessionItem = SessionDetailsFragmentArgs.fromBundle(requireArguments()).session

        binding.session = sessionItem
        binding.lifecycleOwner = viewLifecycleOwner
        navController = findNavController()
    }

    companion object {
        const val TAG = "SessionDetailsFragment"
    }
}