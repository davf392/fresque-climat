package com.davidfz.fresqueclimat.ui.profile.edit_profile

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.davidfz.fresqueclimat.data.remote.repositories.ProfileRepositoryImpl
import dagger.hilt.android.lifecycle.HiltViewModel
//import com.davidfz.fresqueclimat.data.remote.repositories.ProfileRepositoryImpl
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ProfileEditViewModel @Inject constructor(
    repository: ProfileRepositoryImpl
)
    : ViewModel() {

    val profile = repository.getUserProfile()

    init {
        viewModelScope.launch {
            Log.d("ProfileEditViewModel", "profile: $profile")
        }
    }
}