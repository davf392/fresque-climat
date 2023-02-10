package com.idplus.fresqueclimat.ui.animator

import android.os.Bundle
import android.view.LayoutInflater
import android.view.Menu
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.Navigation
import com.idplus.fresqueclimat.R
import com.idplus.fresqueclimat.databinding.FragmentAnimAccountBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class AnimAccountFragment : Fragment() {

    private lateinit var binding: FragmentAnimAccountBinding
    private val navController by lazy { Navigation.findNavController(requireActivity(), R.id.nav_host_fragment_anim) }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val fragmentBinding = FragmentAnimAccountBinding.inflate(inflater, container, false)
        binding = fragmentBinding
        return fragmentBinding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        // Listen bottom navigation tabs change
        binding.bottomNavView.setOnItemSelectedListener {
            navController.popBackStack()
            when (it.itemId) {
                R.id.miNews -> navController.navigate(R.id.newsFragment)
                R.id.miSessions -> navController.navigate(R.id.sessionFragment)
                R.id.miResources -> navController.navigate(R.id.resourcesFragment)
            }
            true
        }
    }


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setHasOptionsMenu(true)
    }

    override fun onPrepareOptionsMenu(menu: Menu) {
        super.onPrepareOptionsMenu(menu)
        menu.findItem(R.id.menu_anim_space).isVisible = false
    }
}