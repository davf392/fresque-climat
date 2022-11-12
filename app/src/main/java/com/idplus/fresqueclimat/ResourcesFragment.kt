package com.idplus.fresqueclimat

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.idplus.fresqueclimat.adapter.ResourceItemAdapter
import com.idplus.fresqueclimat.data.FreskDatabase
import com.idplus.fresqueclimat.databinding.FragmentResourcesBinding
import com.idplus.fresqueclimat.factory.ResourceViewModelFactory
import com.idplus.fresqueclimat.viewmodel.ResourceViewModel


/**
 * A simple [Fragment] subclass.
 * Use the [ResourcesFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class ResourcesFragment : Fragment() {

    private var _binding: FragmentResourcesBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(inf: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        _binding = FragmentResourcesBinding.inflate(inf, container, false)
        val view = binding.root

        val application = requireNotNull(this.activity).application

        // get an instance of the database and the object to the DAO we will use to manage sessions
        val dao = FreskDatabase.getInstance(application).resourceDao

        // builds the view model for the sessions with the factory
        val viewModel = ViewModelProvider(this, ResourceViewModelFactory(dao)).get(ResourceViewModel::class.java)

        binding.viewModel = viewModel
        binding.lifecycleOwner = viewLifecycleOwner

        // insert fake sessions into the database
        viewModel.seedFakeData()

        // add the adapter to the sessionsList recycler view
        val adapter = ResourceItemAdapter()

        binding.resourcesList.adapter = adapter

        binding.resourcesList.setOnClickListener(View.OnClickListener {
            var itemPosition = binding.resourcesList.indexOfChild(it)
        })

        viewModel.resources.observe(viewLifecycleOwner, Observer {
            it?.let {
                adapter.data = it
            }
        })

        return view
    }
}
