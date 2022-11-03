package com.idplus.fresqueclimat

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.idplus.fresqueclimat.data.SessionDatabase
import com.idplus.fresqueclimat.databinding.FragmentSessionBinding


/**
 * A simple [Fragment] subclass.
 * Use the [SessionFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class SessionFragment : Fragment() {

    private var _binding: FragmentSessionBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(inf: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        _binding = FragmentSessionBinding.inflate(inf, container, false)
        val view = binding.root

        val application = requireNotNull(this.activity).application

        // get an instance of the database and the object to the DAO we will use to manage sessions
        val dao = SessionDatabase.getInstance(application).sessionDao

        // builds the view model for the sessions with the factory
        val viewModel = ViewModelProvider(this, SessionViewModelFactory(dao)).get(SessionViewModel::class.java)

        binding.viewModel = viewModel
        binding.lifecycleOwner = viewLifecycleOwner

        viewModel.seedFakeData()

        // add the adapter to the sessionsList recycler view
        val adapter = SessionItemAdapter()
        binding.sessionsList.adapter = adapter

        viewModel.sessions.observe(viewLifecycleOwner, Observer {
            it?.let {
                adapter.data = it
            }
        })

        return view
    }

}