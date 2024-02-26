package com.davidfz.fresqueclimat.ui.profile

import android.net.Uri
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.davidfz.fresqueclimat.data.remote.model.Profile
import com.davidfz.fresqueclimat.data.remote.repositories.ProfileRepositoryImpl
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ProfileViewModel @Inject constructor(
    private val repository: ProfileRepositoryImpl
)
    : ViewModel() {

    private val TAG = "ProfileViewModel"

    var selectedLanguages = mutableListOf<String>()
    val availableLanguages = listOf("Anglais", "Français", "Espagnol")

    val profile = repository.getUserProfile()

    private val _selectedProfilePicture= MutableLiveData <Uri?>()
    val selectedProfilePicture: LiveData<Uri?> get() = _selectedProfilePicture

    private val _navigateToProfileEdit = MutableLiveData<Profile?>()
    val navigateToProfileEdit: LiveData<Profile?> get() = _navigateToProfileEdit

    private val _isProfileSaved = MutableLiveData<Boolean>()
    val isProfileSaved: LiveData<Boolean> get() = _isProfileSaved

    private val _countryCode = MutableLiveData<String>()
    val countryCode: LiveData<String> get() = _countryCode

    private val _phoneNumber = MutableLiveData<String>()
    val phoneNumber: LiveData<String> get() = _phoneNumber


    fun updateChanges() = viewModelScope.launch {
        profile.value?.let { profile ->
            if (_selectedProfilePicture.value != null) {
                profile.profilePictureUri = _selectedProfilePicture.value
            }
            profile.phoneNumber = "${_countryCode.value}${_phoneNumber.value}"
            profile.languages = selectedLanguages
            repository.updateUserProfile(profile)
        }
    }

    fun savePhoneNumber(phoneNumber: String) {
        _phoneNumber.value = phoneNumber
    }

    fun setCountryCode(phoneCode: String) {
        _countryCode.value = phoneCode
    }

    // Method to trigger navigation to profile edit screen
    fun onEditProfileClicked(profile: Profile) {
        _navigateToProfileEdit.value = profile
    }

    fun doneNavigating() {
        _navigateToProfileEdit.value = null
    }

    fun saveProfile() {
        _isProfileSaved.value = true
    }

    fun removeLanguage(languageToRemove: String?) {
        if (languageToRemove != null) {
            selectedLanguages.remove(languageToRemove)
        }
    }

    fun saveProfilePicture(pictureUri: Uri?) {
        _selectedProfilePicture.value = pictureUri
    }
}