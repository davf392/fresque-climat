package com.davidfz.fresqueclimat.ui.profile

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class Language(val id: Int, val name: String) : Parcelable {
    override fun toString(): String {
        return name
    }
}