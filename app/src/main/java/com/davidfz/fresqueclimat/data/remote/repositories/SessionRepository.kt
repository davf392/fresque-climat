package com.davidfz.fresqueclimat.data.remote.repositories

import androidx.lifecycle.LiveData
import com.davidfz.fresqueclimat.ui.explore.SessionItem

interface SessionRepository {

    fun observeAllSessions(): LiveData<List<SessionItem>>

    suspend fun refreshSessions()
}