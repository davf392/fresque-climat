package com.davidfz.fresqueclimat.data.remote.repositories

import androidx.lifecycle.LiveData
import androidx.lifecycle.map
import com.davidfz.fresqueclimat.data.local.dao.ProfileDao
import com.davidfz.fresqueclimat.data.local.entities.asDatabaseModel
import com.davidfz.fresqueclimat.data.local.entities.asDomainModel
import com.davidfz.fresqueclimat.data.remote.model.Profile
import javax.inject.Inject

class ProfileRepositoryImpl @Inject constructor(
    private val dao: ProfileDao
)
 : ProfileRepository {

    private val TAG = "ProfileRepositoryImpl"

    override fun getUserProfile(): LiveData<Profile?> {
        return dao.get().map { it?.asDomainModel() }
    }

    override suspend fun updateUserProfile(updatedProfile: Profile) {
        dao.update(updatedProfile.asDatabaseModel())
    }

    override suspend fun insertProfile(newProfile: Profile?) {

    }
}