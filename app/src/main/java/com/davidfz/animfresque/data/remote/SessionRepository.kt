package com.davidfz.animfresque.data.remote

import androidx.lifecycle.LiveData
import com.davidfz.animfresque.ui.explore.SessionItem

interface SessionRepository {

    fun observeAllSessions(): LiveData<List<SessionItem>>

    suspend fun refreshSessions()
}