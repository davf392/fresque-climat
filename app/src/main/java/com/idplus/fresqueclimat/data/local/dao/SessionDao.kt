package com.idplus.fresqueclimat.data.local.dao

import androidx.lifecycle.LiveData
import androidx.room.*
import com.idplus.fresqueclimat.data.local.entities.Session

@Dao
interface SessionDao {

    @Insert
    suspend fun insert(session: Session)

    @Update
    suspend fun update(session: Session)

    @Delete
    suspend fun delete(session: Session)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAll(sessions: Array<Session>)

    @Query("SELECT * FROM session WHERE sessionId = :key")
    fun get(key: Long): LiveData<Session>

    @Query("SELECT * FROM session ORDER BY sessionId DESC")
    fun getAll(): LiveData<List<Session>>

    @Query("SELECT * FROM session WHERE (capacity_participants- total_participants) > 0 ORDER BY date ASC")
    fun getAvailableForParticipantsByDate(): LiveData<List<Session>>
}
