package com.davidfz.fresqueclimat.ui.profile

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.NavController
import androidx.navigation.fragment.findNavController
import com.bumptech.glide.Glide
import com.davidfz.fresqueclimat.R
import com.davidfz.fresqueclimat.adapters.LanguageAdapter
import com.davidfz.fresqueclimat.databinding.FragmentProfileEditBinding
import com.github.dhaval2404.imagepicker.ImagePicker
import dagger.hilt.android.AndroidEntryPoint


@AndroidEntryPoint
class ProfileEditFragment : Fragment() {

    private lateinit var binding: FragmentProfileEditBinding
    private lateinit var navController: NavController
    private val profileViewModel by viewModels<ProfileViewModel>()

    private var selectedLanguages = mutableListOf<String>()
    private val availableLanguages = listOf("Anglais", "Français", "Espagnol")

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val fragmentBinding = FragmentProfileEditBinding.inflate(inflater, container, false)
        binding = fragmentBinding
        return fragmentBinding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.viewModel = profileViewModel
        binding.profile = profileViewModel.profile.value
        binding.lifecycleOwner = viewLifecycleOwner
        navController = findNavController()

        setupProfilePicture()
        setupLanguageAdapter()
        observeLanguageChips()

        binding.fragment = this@ProfileEditFragment
    }

    private fun observeLanguageChips() {
        profileViewModel.profile.observe(viewLifecycleOwner) { profile ->
            profile?.languages?.let { languages ->
                for (language in languages) {
                    addChip(language)
                }
            }
        }
    }

    private fun setupProfilePicture() {
        binding.buttonAddProfilePicture.setOnClickListener {
            ImagePicker.with(this)
                .crop()	    			                // crop image(optional), check customization for more option
                .compress(1024)			        // final image size will be less than 1 MB(Optional)
                .maxResultSize(1080, 1080)	// final image resolution will be less than 1080 x 1080 (Optional)
                .start()
        }
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        Glide.with(this)
            .load(data?.data)
            .circleCrop()
            .error(R.drawable.ic_anim_person)
            .into(binding.profilePicture)
    }

    private fun setupLanguageAdapter() {
        val adapter = LanguageAdapter(
            requireContext(),
            android.R.layout.simple_dropdown_item_1line,
            availableLanguages.toList(),
            selectedLanguages
        )
        binding.autoCompleteLanguages.setAdapter(adapter)
        binding.autoCompleteLanguages.threshold = 1 // Set the number of characters to start filtering
        binding.autoCompleteLanguages.setOnItemClickListener { _, _, position, _ ->
            val selectedLanguage = adapter.getItem(position)
            if (selectedLanguage != null) {
                selectedLanguages.add(selectedLanguage)
                addChip(selectedLanguage)
                binding.autoCompleteLanguages.text.clear()
                adapter.updateData(availableLanguages.toList(), selectedLanguages)
            }
        }
    }

    private fun addChip(languageName: String) {
        val chipView = LayoutInflater.from(requireContext()).inflate(R.layout.language_chip, binding.flexboxLanguages, false)
        val chipText = chipView.findViewById<TextView>(R.id.chipText)
        chipText.text = languageName

        chipView.findViewById<TextView>(R.id.chipCloseButton).setOnClickListener {
            binding.flexboxLanguages.removeView(chipView)
            val languageToRemove = selectedLanguages.find { it == languageName }
            if (languageToRemove != null) {
                selectedLanguages.remove(languageToRemove)
            }
        }
        binding.flexboxLanguages.addView(chipView)
    }
}