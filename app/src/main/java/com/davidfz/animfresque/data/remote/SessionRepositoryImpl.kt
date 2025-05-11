package com.davidfz.animfresque.data.remote

import androidx.lifecycle.LiveData
import com.davidfz.animfresque.ui.explore.SessionItem

class SessionRepositoryImpl: SessionRepository {
    override fun observeAllSessions(): LiveData<List<SessionItem>> {
        TODO("Not yet implemented")
    }

    override suspend fun refreshSessions() {
        TODO("Not yet implemented")
    }


}