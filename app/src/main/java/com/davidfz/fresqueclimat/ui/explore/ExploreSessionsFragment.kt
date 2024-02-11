package com.davidfz.fresqueclimat.ui.explore

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.navigation.NavController
import androidx.navigation.fragment.findNavController
import com.davidfz.fresqueclimat.databinding.FragmentExploreSessionsBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class ExploreSessionsFragment : Fragment() {

    private lateinit var binding: FragmentExploreSessionsBinding
    private lateinit var navController: NavController
    private lateinit var sessionListAdapter: SessionItemAdapter
    private val exploreSessionsViewModel by viewModels<ExploreSessionsViewModel>()

    override fun onCreateView(inf: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        val fragmentBinding = FragmentExploreSessionsBinding.inflate(inf, container, false)
        binding = fragmentBinding
        return fragmentBinding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.viewModel = exploreSessionsViewModel
        binding.lifecycleOwner = viewLifecycleOwner

        navController = findNavController()
        setupRecyclerView()
        setupObservers()
    }

    private fun setupRecyclerView() {
        sessionListAdapter = SessionItemAdapter(SessionItemAdapter.SessionListener { session ->
            exploreSessionsViewModel.onSessionClicked(session)
        })
        binding.rvSessionsList.adapter = sessionListAdapter
    }

    private fun setupObservers() {
        // observe & submit the list of sessions to the adapter whenever it changes
        exploreSessionsViewModel.sessions.observe(viewLifecycleOwner) { sessions ->
            if (sessions != null) {
                sessionListAdapter.submitList(sessions)
            }
        }

        exploreSessionsViewModel.navigateToSessionDetails.observe(viewLifecycleOwner) { session ->
            if(session != null) {
                navigateToSessionDetails(session)
                exploreSessionsViewModel.doneNavigating()
            }
        }
    }

    private fun navigateToSessionDetails(session: SessionItem) {
        navController.navigate(
            ExploreSessionsFragmentDirections.actionExploreSessionsFragmentToSessionDetailsFragment(
                session
            )
        )
    }
}