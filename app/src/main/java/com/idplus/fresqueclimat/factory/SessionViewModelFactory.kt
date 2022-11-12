package com.idplus.fresqueclimat.factory

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.idplus.fresqueclimat.data.SessionDao
import com.idplus.fresqueclimat.viewmodel.SessionViewModel

class SessionViewModelFactory(private val dao: SessionDao) : ViewModelProvider.Factory {

    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if(modelClass.isAssignableFrom(SessionViewModel::class.java)) {
            return SessionViewModel(dao) as T
        }
        throw IllegalArgumentException("Unknown ViewModel")
    }
}
