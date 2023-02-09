package com.idplus.fresqueclimat.data.remote.repositories

import androidx.lifecycle.LiveData
import com.idplus.fresqueclimat.data.local.entities.Session

interface SessionRepository {

    fun observeAllSessions(): LiveData<List<Session>>

    suspend fun refreshSessions()
}