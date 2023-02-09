package com.idplus.fresqueclimat.data.remote.repositories

import androidx.lifecycle.LiveData
import com.idplus.fresqueclimat.data.local.entities.Resource

interface ResourcesRepository {

    fun observeAllResources(): LiveData<List<Resource>>

    suspend fun refreshResources()
}