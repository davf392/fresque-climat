package com.idplus.fresqueclimat.ui.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.idplus.fresqueclimat.data.remote.repositories.ResourcesRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject


@HiltViewModel
class ResourceViewModel @Inject constructor(
    private val repository: ResourcesRepository
)
    : ViewModel() {

    val resources = repository.observeAllResources()

    init {
        viewModelScope.launch {
            repository.refreshResources()
        }
    }
}
