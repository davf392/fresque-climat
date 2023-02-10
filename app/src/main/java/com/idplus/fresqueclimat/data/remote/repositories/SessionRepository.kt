package com.idplus.fresqueclimat.data.remote.repositories

import androidx.lifecycle.LiveData
import com.idplus.fresqueclimat.ui.sessions.SessionItem

interface SessionRepository {

    fun observeAllSessions(): LiveData<List<SessionItem>>

    suspend fun refreshSessions()
}