package com.idplus.fresqueclimat.data

import androidx.lifecycle.LiveData
import androidx.room.*

@Dao
interface ResourceDao {

    @Insert
    suspend fun insert(resource: Resource)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    @JvmSuppressWildcards
    suspend fun insertAll(resources: Array<Resource>)

    @Update
    suspend fun update(resource: Resource)

    @Delete
    suspend fun delete(resource: Resource)

    @Query("SELECT * FROM resources WHERE resourceId = :key")
    fun get(key: Long): LiveData<Resource>

    @Query("SELECT * FROM resources")
    fun getAll(): LiveData<List<Resource>>
}
