package com.davidfz.fresqueclimat.data.local.dao

import androidx.lifecycle.LiveData
import androidx.room.*
import com.davidfz.fresqueclimat.data.local.entities.ResourceEntity

@Dao
interface ResourceDao {

    @Insert
    suspend fun insert(resource: ResourceEntity)

    @Update
    suspend fun update(resource: ResourceEntity)

    @Delete
    suspend fun delete(resource: ResourceEntity)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAll(resources: Array<ResourceEntity>)

    @Query("SELECT * FROM resources WHERE resourceId = :key")
    fun get(key: Long): LiveData<ResourceEntity>

    @Query("SELECT * FROM resources")
    fun getAll(): LiveData<List<ResourceEntity>>
}
