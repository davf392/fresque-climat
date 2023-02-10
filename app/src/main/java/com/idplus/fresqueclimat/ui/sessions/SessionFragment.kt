package com.idplus.fresqueclimat.ui.sessions

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.navigation.NavController
import androidx.navigation.fragment.findNavController
import com.idplus.fresqueclimat.databinding.FragmentSessionBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class SessionFragment : Fragment() {

    private lateinit var binding: FragmentSessionBinding
    private lateinit var navController: NavController
    private val sessionViewModel by viewModels<SessionViewModel>()
    private lateinit var sessionListAdapter: SessionItemAdapter

    override fun onCreateView(inf: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        val fragmentBinding = FragmentSessionBinding.inflate(inf, container, false)
        binding = fragmentBinding
        return fragmentBinding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.viewModel = sessionViewModel
        binding.lifecycleOwner = viewLifecycleOwner

        navController = findNavController()
        setupRecyclerView()
        setupObservers()
    }

    private fun setupRecyclerView() {
        sessionListAdapter = SessionItemAdapter(SessionItemAdapter.SessionListener { session ->
            sessionViewModel.onSessionClicked(session)
        })
        binding.rvSessionsList.adapter = sessionListAdapter
    }

    private fun setupObservers() {
        // observe & submit the list of asteroids to the adapter whenever it changes
        sessionViewModel.sessions.observe(viewLifecycleOwner) { sessions ->
            if (sessions != null) {
                sessionListAdapter.submitList(sessions)
            }
        }

        sessionViewModel.navigateToSessionDetails.observe(viewLifecycleOwner) { session ->
            if(session != null) {
                navigateToSessionDetails(session)
            }
        }
    }

    private fun navigateToSessionDetails(session: SessionItem) {
        navController.navigate(
            SessionFragmentDirections.actionSessionFragmentToSessionDetailsFragment(
                session
            )
        )
    }
}