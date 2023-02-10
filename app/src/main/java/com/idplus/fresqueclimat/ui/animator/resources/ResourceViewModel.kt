package com.idplus.fresqueclimat.ui.animator.resources

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.idplus.fresqueclimat.data.remote.repositories.ResourcesRepositoryImpl
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject


@HiltViewModel
class ResourceViewModel @Inject constructor(
    private val repository: ResourcesRepositoryImpl
)
    : ViewModel() {

    val resources = repository.observeAllResources()

    init {
        viewModelScope.launch {
            repository.refreshResources()
        }
    }
}
