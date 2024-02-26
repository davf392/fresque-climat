package com.davidfz.fresqueclimat.data.remote.model

import android.net.Uri
import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class Profile (
    var profileId: Long,
    var firstName: String,
    var lastName: String,
    var homeCity: String,
    var email: String,
    var phoneNumber: String,
    var languages: List<String>,
    var isPublic: Boolean,
    var isAnimatingInCompany: Boolean,
    var isAnimatingAsCommercial: Boolean,
    var profilePictureUri: Uri?
)
    : Parcelable