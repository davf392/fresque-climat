package com.davidfz.fresqueclimat.ui.profile

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.navigation.NavController
import androidx.navigation.fragment.findNavController
import com.davidfz.adapters.BaseMenuAdapter
import com.davidfz.fresqueclimat.databinding.FragmentProfileBinding
import dagger.hilt.android.AndroidEntryPoint


@AndroidEntryPoint
class ProfileFragment : Fragment() {

    private lateinit var binding: FragmentProfileBinding
    private lateinit var navController: NavController

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val fragmentBinding = FragmentProfileBinding.inflate(inflater, container, false)
        binding = fragmentBinding
        return fragmentBinding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        navController = findNavController()

        setupRecyclerView()

        binding.fragment = this@ProfileFragment
    }

    private fun setupRecyclerView() {
        val list = mutableListOf<String>()
        for (value in ProfileFeat.values())
            list.add(value.nameItem)

        val listAdapter = BaseMenuAdapter(requireContext(), list)
        binding.listProfileMenuItems.adapter = listAdapter
        binding.listProfileMenuItems.setOnItemClickListener { adapterView, _, pos, _ ->
            when(adapterView.getItemAtPosition(pos)) {
                ProfileFeat.SESSIONS.nameItem -> Toast.makeText(activity, "go to list of profile sessions", Toast.LENGTH_SHORT).show()
                ProfileFeat.REVIEWS.nameItem -> Toast.makeText(activity, "go to list of profile reviews", Toast.LENGTH_SHORT).show()
                ProfileFeat.PROGRESS.nameItem -> Toast.makeText(activity, "go to progress screen", Toast.LENGTH_SHORT).show()
                ProfileFeat.CHEATSHEET.nameItem -> Toast.makeText(activity, "go to cheatsheet screen", Toast.LENGTH_SHORT).show()
                ProfileFeat.LOGOUT.nameItem -> navController.navigateUp()
            }
        }
    }

    fun goToProfileInfo() {
//        navController.navigate()
    }
}