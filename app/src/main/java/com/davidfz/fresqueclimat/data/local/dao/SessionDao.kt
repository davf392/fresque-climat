package com.davidfz.fresqueclimat.data.local.dao

import androidx.lifecycle.LiveData
import androidx.room.*
import com.davidfz.fresqueclimat.data.local.entities.SessionEntity

@Dao
interface SessionDao {

    @Insert
    suspend fun insert(session: SessionEntity)

    @Update
    suspend fun update(session: SessionEntity)

    @Delete
    suspend fun delete(session: SessionEntity)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAll(sessions: Array<SessionEntity>)

    @Query("SELECT * FROM session WHERE sessionId = :key")
    fun get(key: Long): LiveData<SessionEntity>

    @Query("SELECT * FROM session ORDER BY sessionId DESC")
    fun getAll(): LiveData<List<SessionEntity>>

    @Query("SELECT * FROM session WHERE (capacity_participants- total_participants) > 0 ORDER BY date ASC")
    fun getAvailableForParticipantsByDate(): LiveData<List<SessionEntity>>
}
