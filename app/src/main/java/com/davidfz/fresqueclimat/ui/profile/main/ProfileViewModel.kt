package com.davidfz.fresqueclimat.ui.profile.main

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

    val profile = repository.getUserProfile()

    private val _navigateToProfileEdit = MutableLiveData<Profile?>()
    val navigateToProfileEdit: LiveData<Profile?> get() = _navigateToProfileEdit

    private val _userFirstName = MutableLiveData<String>()
    val userFirstName: LiveData<String> get() = _userFirstName

    private val _userCity = MutableLiveData<String>()
    val userCity: LiveData<String> get() = _userCity

    init {
        viewModelScope.launch {
            repository.refreshProfile()
        }
    }

    // Method to trigger navigation to profile edit screen
    fun onEditProfileClicked(profile: Profile) {
        _navigateToProfileEdit.value = profile
    }

    fun doneNavigating() {
        _navigateToProfileEdit.value = null
    }
}