package com.davidfz.fresqueclimat.ui.profile.edit_profile

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
//import com.davidfz.fresqueclimat.data.remote.repositories.ProfileRepositoryImpl
import kotlinx.coroutines.launch
import javax.inject.Inject

class ProfileEditViewModel @Inject constructor(
//    private val repository: ProfileRepositoryImpl
)
    : ViewModel() {

//    val resources = repository.observeAllResources()

    init {
        viewModelScope.launch {
//            repository.refreshProfileInfo()
        }
    }
}