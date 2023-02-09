package com.idplus.fresqueclimat.ui.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.idplus.fresqueclimat.data.remote.repositories.SessionRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class SessionViewModel @Inject constructor(
    private val repository: SessionRepository
)
    : ViewModel() {

    val sessions = repository.observeAllSessions()

    init {
        viewModelScope.launch {
            repository.refreshSessions()
        }
    }
}
