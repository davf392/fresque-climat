package com.davidfz.fresqueclimat.ui.profile

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.util.Log
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
import com.google.android.material.snackbar.Snackbar
import dagger.hilt.android.AndroidEntryPoint


@AndroidEntryPoint
class ProfileEditFragment : Fragment() {

    private val TAG = "ProfileEditFragment"
    private lateinit var binding: FragmentProfileEditBinding
    private lateinit var navController: NavController
    private val profileViewModel by viewModels<ProfileViewModel>()

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
        setupProfileObserver()
        setupCountryCodeAndPhoneNumber()
        setupSaveProfileButton()

        binding.fragment = this@ProfileEditFragment
    }

    private fun setupCountryCodeAndPhoneNumber() {
        binding.countryCodePhoneNumber.registerCarrierNumberEditText(binding.editPhoneNumber)
        binding.countryCodePhoneNumber.setOnCountryChangeListener {
            Log.d(TAG, "selected country code: ${binding.countryCodePhoneNumber.selectedCountryCodeWithPlus}")
            profileViewModel.setCountryCode(binding.countryCodePhoneNumber.selectedCountryCodeWithPlus)
        }
    }

    private fun setupProfileObserver() {
        profileViewModel.profile.observe(viewLifecycleOwner) { profile ->
            // init language chips
            profile?.languages?.let { languages ->
                for (language in languages) {
                    if (language !in profileViewModel.selectedLanguages)
                        addChip(language)
                }
            }
            profile?.profilePictureUri?.let { picture ->
                Log.d(TAG, "loading profile picture : $picture")
                loadPicture(picture)
            }

            // setup country code picker and phone edit text
            binding.countryCodePhoneNumber.fullNumber = profile?.phoneNumber ?: ""
            Log.d(TAG, "profile phone number is : ${profile?.phoneNumber}")
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

    private fun setupSaveProfileButton() {
        binding.btnSaveChanges.setOnClickListener {
            Log.d(TAG, "saving changes")

            // remove white spaces in phone number and save it
            val phone = binding.editPhoneNumber.text.toString().filter { !it.isWhitespace() }
            profileViewModel.savePhoneNumber(phone)
            profileViewModel.updateChanges()
            profileViewModel.saveProfile()
        }

        profileViewModel.isProfileSaved.observe(viewLifecycleOwner) { isSaved ->
            if (isSaved) {
                // display a snackbar to inform the user and navigate back to previous fragment
                findNavController().popBackStack()
                Snackbar.make(binding.root, "Profile information saved", Snackbar.LENGTH_SHORT).show()
            }
        }
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        loadPicture(data?.data)
        profileViewModel.saveProfilePicture(data?.data)
    }

    private fun loadPicture(data: Uri?) {
        Glide.with(this)
            .load(data)
            .circleCrop()
            .error(R.drawable.ic_anim_person)
            .into(binding.profilePicture)
    }

    private fun setupLanguageAdapter() {
        val adapter = LanguageAdapter(
            requireContext(),
            android.R.layout.simple_dropdown_item_1line,
            profileViewModel.availableLanguages.toList(),
            profileViewModel.selectedLanguages
        )
        binding.autoCompleteLanguages.setAdapter(adapter)
        binding.autoCompleteLanguages.threshold = 1 // Set the number of characters to start filtering
        binding.autoCompleteLanguages.setOnItemClickListener { _, _, position, _ ->
            val selectedLanguage = adapter.getItem(position)
            if (selectedLanguage != null) {
                addChip(selectedLanguage)
                binding.autoCompleteLanguages.text.clear()
                adapter.updateData(
                    profileViewModel.availableLanguages.toList(),
                    profileViewModel.selectedLanguages
                )
            }
        }
    }

    private fun addChip(languageName: String) {
        val chipView = LayoutInflater.from(requireContext()).inflate(R.layout.language_chip, binding.flexboxLanguages, false)
        val chipText = chipView.findViewById<TextView>(R.id.chipText)
        chipText.text = languageName

        chipView.findViewById<TextView>(R.id.chipCloseButton).setOnClickListener {
            binding.flexboxLanguages.removeView(chipView)
            profileViewModel.removeLanguage(profileViewModel.selectedLanguages.find { it == languageName })
        }
        binding.flexboxLanguages.addView(chipView)
        profileViewModel.selectedLanguages.add(languageName)
    }
}