package com.idplus.fresqueclimat.data

import androidx.lifecycle.LiveData
import androidx.room.*

@Dao
interface SessionDao {

    @Insert
    suspend fun insert(session: Session)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    @JvmSuppressWildcards
    suspend fun insertAll(sessions: Array<Session>)

    @Update
    suspend fun update(session: Session)

    @Delete
    suspend fun delete(session: Session)

    @Query("SELECT * FROM session WHERE sessionId = :key")
    fun get(key: Long): LiveData<Session>

    @Query("SELECT * FROM session ORDER BY sessionId DESC")
    fun getAll(): LiveData<List<Session>>
}
