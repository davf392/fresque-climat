package com.davidfz.fresqueclimat.data.remote.repositories

import androidx.lifecycle.LiveData
import com.davidfz.fresqueclimat.data.remote.model.Profile

interface ProfileRepository {
    fun getUserProfile(): LiveData<Profile?>
    suspend fun updateUserProfile(updatedProfile: Profile)
    suspend fun insertProfile(newProfile: Profile?)
}