package com.davidfz.fresqueclimat.data.remote.repositories

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.davidfz.fresqueclimat.data.remote.model.Profile

class FakeProfileRepository: ProfileRepository {

    private val TAG = "FakeProfileRepository"

    private val _profile = MutableLiveData<Profile?>()
    val profile: LiveData<Profile?> get() = _profile

    companion object {
        val FAKE_PROFILE = Profile(
            1L,
            "David",
            "Fourdrigniez",
            "Lyon",
            "davidfourdrigniez@protonmail.com",
            "+33627940325",
            listOf("French", "English"),
            true,
            false,
            false
        )
    }

    override fun getUserProfile(): LiveData<Profile?> {
        return profile
    }

    override suspend fun updateUserProfile(updatedProfile: Profile) {
        _profile.value = updatedProfile
    }

    override suspend fun insertProfile(newProfile: Profile?) {
        _profile.value = newProfile
    }
}