package com.idplus.fresqueclimat.ui.explore.participate

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.Menu
import android.view.View
import android.view.ViewGroup
import androidx.navigation.NavController
import androidx.navigation.fragment.findNavController
import com.idplus.fresqueclimat.R
import com.idplus.fresqueclimat.databinding.FragmentSelectParticipationBinding


class SelectParticipationFragment : Fragment() {

    private lateinit var binding: FragmentSelectParticipationBinding
    private lateinit var navController: NavController

    override fun onCreateView(inf: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val fragmentBinding = FragmentSelectParticipationBinding.inflate(inf, container, false)
        binding = fragmentBinding
        return fragmentBinding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.fragment = this@SelectParticipationFragment
        binding.lifecycleOwner = viewLifecycleOwner
        navController = findNavController()
    }

    fun goToPublicSessionsList() {
        navController.navigate(R.id.action_selectParticipationFragment_to_exploreSessionsFragment)
    }
}