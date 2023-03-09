package com.davidfz.fresqueclimat.data.local.dao

import androidx.lifecycle.LiveData
import androidx.room.*
import com.davidfz.fresqueclimat.data.local.entities.Resource

@Dao
interface ResourceDao {

    @Insert
    suspend fun insert(resource: Resource)

    @Update
    suspend fun update(resource: Resource)

    @Delete
    suspend fun delete(resource: Resource)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAll(resources: Array<Resource>)

    @Query("SELECT * FROM resources WHERE resourceId = :key")
    fun get(key: Long): LiveData<Resource>

    @Query("SELECT * FROM resources")
    fun getAll(): LiveData<List<Resource>>
}
