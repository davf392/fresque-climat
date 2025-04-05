package com.davidfz.animfresque.ui.explore

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch

class ExploreViewModel : ViewModel() {

//    val sessions = repository.observeAllSessions()

    // expose the event triggered by the user in a live data to the UI
    private val _navigateToSessionDetails = MutableLiveData<SessionItem?>()
    val navigateToSessionDetails: LiveData<SessionItem?> get() = _navigateToSessionDetails

    init {
        viewModelScope.launch {
//            repository.refreshSessions()
        }
    }

    fun onSessionClicked(sessionItem: SessionItem) {
        _navigateToSessionDetails.value = sessionItem
    }

    fun doneNavigating() {
        _navigateToSessionDetails.value = null
    }
}
