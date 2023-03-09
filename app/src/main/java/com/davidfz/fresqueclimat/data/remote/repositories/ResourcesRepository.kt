package com.davidfz.fresqueclimat.data.remote.repositories

import androidx.lifecycle.LiveData
import com.davidfz.fresqueclimat.data.local.entities.Resource

interface ResourcesRepository {

    fun observeAllResources(): LiveData<List<Resource>>

    suspend fun refreshResources()
}