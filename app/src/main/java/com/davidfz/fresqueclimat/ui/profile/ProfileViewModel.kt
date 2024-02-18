package com.davidfz.fresqueclimat.ui.profile

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.davidfz.fresqueclimat.data.local.entities.ProfileItem
import com.davidfz.fresqueclimat.data.remote.model.Profile
import com.davidfz.fresqueclimat.data.remote.repositories.ProfileRepositoryImpl
import dagger.hilt.android.lifecycle.HiltViewModel
//import com.davidfz.fresqueclimat.data.remote.repositories.ProfileRepositoryImpl
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ProfileViewModel @Inject constructor(
    private val repository: ProfileRepositoryImpl
)
    : ViewModel() {

    var selectedLanguages = mutableListOf<String>()
    val availableLanguages = listOf("Anglais", "Français", "Espagnol")

    val profile = repository.getUserProfile()

    private val _navigateToProfileEdit = MutableLiveData<Profile?>()
    val navigateToProfileEdit: LiveData<Profile?> get() = _navigateToProfileEdit

    private val _countryCode = MutableLiveData<String>()
    val countryCode: LiveData<String> get() = _countryCode

    private val _phoneNumber = MutableLiveData<String>()
    val phoneNumber: LiveData<String> get() = _phoneNumber

    init {
        viewModelScope.launch {
            repository.refreshProfile()
        }
    }

    fun updateChanges() = viewModelScope.launch {
        profile.value?.let { profile ->
            profile.phoneNumber = "${_countryCode.value}${_phoneNumber.value}"
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
}