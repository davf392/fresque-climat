package com.davidfz.fresqueclimat.data.local.dao

import androidx.lifecycle.LiveData
import androidx.room.*
import com.davidfz.fresqueclimat.data.local.entities.ProfileItem

@Dao
interface ProfileDao {

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insert(profile: ProfileItem)

    @Update
    suspend fun update(profile: ProfileItem)

    @Delete
    suspend fun delete(profile: ProfileItem)

    @Query("SELECT * FROM profile LIMIT 1")
    fun get(): LiveData<ProfileItem>
}