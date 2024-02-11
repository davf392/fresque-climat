package com.davidfz.fresqueclimat.ui.profile

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
//import com.davidfz.fresqueclimat.data.remote.repositories.ProfileRepositoryImpl
import kotlinx.coroutines.launch
import javax.inject.Inject

class ProfileViewModel @Inject constructor(
//    private val repository: ProfileRepositoryImpl
)
    : ViewModel() {

//    val resources = repository.observeAllResources()

    private val _navigateToProfileEdit = MutableLiveData<UserProfile>()
    val navigateToProfileEdit: LiveData<UserProfile> get() = _navigateToProfileEdit

    init {
        viewModelScope.launch {
//            repository.refreshProfileInfo()
        }
    }

    // Method to trigger navigation to profile edit screen
    fun onEditProfileClicked(profile: UserProfile) {
        _navigateToProfileEdit.value = profile
    }
}