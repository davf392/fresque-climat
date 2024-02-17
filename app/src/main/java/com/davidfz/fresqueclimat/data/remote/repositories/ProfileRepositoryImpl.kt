package com.davidfz.fresqueclimat.data.remote.repositories

import androidx.lifecycle.LiveData
import androidx.lifecycle.map
import com.davidfz.fresqueclimat.data.local.dao.ProfileDao
import com.davidfz.fresqueclimat.data.local.entities.ProfileItem
import com.davidfz.fresqueclimat.data.local.entities.asDatabaseModel
import com.davidfz.fresqueclimat.data.local.entities.asDomainModel
import com.davidfz.fresqueclimat.data.remote.model.Profile
import javax.inject.Inject

class ProfileRepositoryImpl @Inject constructor(
    private val dao: ProfileDao
)
    : ProfileRepository {
    override fun getUserProfile(): LiveData<Profile> {
        return dao.get().map { it.asDomainModel() }
    }

    override suspend fun updateUserProfile(profile: Profile) {
        dao.update(profile.asDatabaseModel())
    }

    override suspend fun refreshProfile() {
        dao.insert(
            ProfileItem(
                0L,
                "David",
                "Fourdrigniez",
                "Alès",
                "davidfourdrigniez@protonmail.com",
                "0627940325",
                listOf("French", "English"),
                true,
                false,
                false
            )
        )

    }
}