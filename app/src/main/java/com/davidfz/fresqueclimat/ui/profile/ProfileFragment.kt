package com.davidfz.fresqueclimat.ui.profile

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.NavController
import androidx.navigation.fragment.findNavController
import com.davidfz.fresqueclimat.adapters.BaseMenuAdapter
import com.davidfz.fresqueclimat.data.remote.model.Profile
import com.davidfz.fresqueclimat.databinding.FragmentProfileBinding
import dagger.hilt.android.AndroidEntryPoint


@AndroidEntryPoint
class ProfileFragment : Fragment() {

    private lateinit var binding: FragmentProfileBinding
    private lateinit var navController: NavController
    private val profileViewModel by viewModels<ProfileViewModel>()

    private val SESSIONS = "Mes sessions à venir"
    private val REVIEWS = "Mes avis"
    private val PROGRESS = "Mon parcours"
    private val CHEATSHEET = "Mon antisèche"
    private val LOGOUT = "Se déconnecter"

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val fragmentBinding = FragmentProfileBinding.inflate(inflater, container, false)
        binding = fragmentBinding
        return fragmentBinding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.viewModel = profileViewModel
        binding.lifecycleOwner = viewLifecycleOwner
        navController = findNavController()

        setupRecyclerView()
        setupObservers()

        binding.fragment = this@ProfileFragment
    }

    private fun setupObservers() {
        profileViewModel.navigateToProfileEdit.observe(viewLifecycleOwner) { profile ->
            if (profile != null) {
                navigateToEditProfile(profile)
                profileViewModel.doneNavigating()
            }
        }
    }


    private fun setupRecyclerView() {
        val list = mutableListOf<String>().apply {
            add(SESSIONS)
            add(REVIEWS)
            add(PROGRESS)
            add(CHEATSHEET)
            add(LOGOUT)
        }

        val listAdapter = BaseMenuAdapter(requireContext(), list)
        binding.listProfileMenuItems.adapter = listAdapter
        binding.listProfileMenuItems.setOnItemClickListener { adapterView, _, pos, _ ->
            when(adapterView.getItemAtPosition(pos)) {
                SESSIONS -> Toast.makeText(activity, "go to list of profile sessions", Toast.LENGTH_SHORT).show()
                REVIEWS -> Toast.makeText(activity, "go to list of profile reviews", Toast.LENGTH_SHORT).show()
                PROGRESS -> Toast.makeText(activity, "go to progress screen", Toast.LENGTH_SHORT).show()
                CHEATSHEET -> Toast.makeText(activity, "go to cheatsheet screen", Toast.LENGTH_SHORT).show()
                LOGOUT -> navController.navigateUp()
            }
        }
    }

    private fun navigateToEditProfile(profile: Profile) {
        navController.navigate(
            ProfileFragmentDirections.actionProfileFragmentToProfileEditFragment(
                profile
            )
        )
    }
}