package com.idplus.fresqueclimat.factory

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.idplus.fresqueclimat.data.ResourceDao
import com.idplus.fresqueclimat.viewmodel.ResourceViewModel

class ResourceViewModelFactory(private val dao: ResourceDao) : ViewModelProvider.Factory {

    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if(modelClass.isAssignableFrom(ResourceViewModel::class.java)) {
            return ResourceViewModel(dao) as T
        }
        throw IllegalArgumentException("Unknown ViewModel")
    }

}
