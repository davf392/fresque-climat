package com.davidfz.fresqueclimat.data.remote.repositories

import androidx.lifecycle.LiveData
import com.davidfz.fresqueclimat.data.local.entities.ResourceEntity

interface ResourcesRepository {

    fun observeAllResources(): LiveData<List<ResourceEntity>>

    suspend fun refreshResources()
}