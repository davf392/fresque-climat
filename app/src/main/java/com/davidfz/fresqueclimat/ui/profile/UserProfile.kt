package com.davidfz.fresqueclimat.ui.profile

import android.os.Parcelable
import kotlinx.parcelize.Parcelize
import kotlinx.parcelize.RawValue

@Parcelize
data class UserProfile(
    val firstName: String,
    val lastName: String,
    val homeCity: String,
    val email: String,
    val phoneNumber: String,
    val languagesSpoken: @RawValue List<Language>,
    val isPublic: Boolean,
    val isAnimatingInCompany: Boolean,
    val isAnimatingAsCommercial: Boolean
)
    : Parcelable