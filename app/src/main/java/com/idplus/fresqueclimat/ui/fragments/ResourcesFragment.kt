package com.idplus.fresqueclimat.ui.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.fragment.app.viewModels
import androidx.lifecycle.ViewModelProvider
import com.idplus.fresqueclimat.ui.adapter.ResourceItemAdapter
import com.idplus.fresqueclimat.databinding.FragmentResourcesBinding
import com.idplus.fresqueclimat.ui.viewmodel.ResourceViewModel
import com.idplus.fresqueclimat.ui.viewmodel.SessionViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class ResourcesFragment : Fragment() {

    private lateinit var binding: FragmentResourcesBinding
    private val resourcesViewModel by viewModels<ResourceViewModel>()

    override fun onCreateView(inf: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        val fragmentBinding = FragmentResourcesBinding.inflate(inf, container, false)
        binding = fragmentBinding
        return fragmentBinding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.viewModel = resourcesViewModel
        binding.lifecycleOwner = viewLifecycleOwner

        // add the adapter to the sessionsList recycler view
        val adapter = ResourceItemAdapter()

        binding.resourcesList.adapter = adapter
        binding.resourcesList.setOnClickListener {
            var itemPosition = binding.resourcesList.indexOfChild(it)
        }

        resourcesViewModel.resources.observe(viewLifecycleOwner) {
            it?.let {
                adapter.data = it
            }
        }
    }
}
