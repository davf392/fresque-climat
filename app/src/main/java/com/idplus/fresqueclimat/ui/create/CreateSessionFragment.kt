package com.idplus.fresqueclimat.ui.create

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.navigation.NavController
import androidx.navigation.fragment.findNavController
import com.idplus.fresqueclimat.databinding.FragmentCreateSessionBinding


class CreateSessionFragment : Fragment() {

    companion object {
        const val TAG = "CreateSessionFragment"
    }

    private val viewModel by viewModels<CreateSessionViewModel>()
    private lateinit var binding: FragmentCreateSessionBinding
    private lateinit var navController: NavController

    override fun onCreateView(inf: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        val fragmentBinding = FragmentCreateSessionBinding.inflate(inf, container, false)
        binding = fragmentBinding
        return fragmentBinding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        navController = findNavController()
    }

    fun goToFillMyCounterFragment() {

    }
}