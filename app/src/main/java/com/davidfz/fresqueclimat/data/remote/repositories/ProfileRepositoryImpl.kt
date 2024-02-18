package com.davidfz.fresqueclimat.data.remote.repositories

import android.util.Log
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

    private val TAG = "ProfileRepositoryImpl"

    override fun getUserProfile(): LiveData<Profile?> {
        return dao.get().map { it?.asDomainModel() }
    }

    override suspend fun updateUserProfile(profile: Profile) {
        dao.update(profile.asDatabaseModel())
    }

    override suspend fun refreshProfile() {
        val firstName = "David"
        val lastName = "Fourdrigniez"
        val email = "davidfourdrigniez@protonmail.com"
        val phone = "+33627940325"
        val city = "Lyon"

        dao.insert(
            ProfileItem(
                1L,
                firstName,
                lastName,
                city,
                email,
                phone,
                listOf("French", "English"),
                true,
                false,
                false
            )
        )
    }
}