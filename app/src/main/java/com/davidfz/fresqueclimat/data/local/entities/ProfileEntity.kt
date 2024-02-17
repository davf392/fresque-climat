package com.davidfz.fresqueclimat.data.local.entities

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.davidfz.fresqueclimat.data.remote.model.Profile

@Entity(tableName = "profile")
class ProfileItem(
    @PrimaryKey(autoGenerate = true)            var profileId: Long = 0L,
    @ColumnInfo(name = "first_name")            var firstName: String,
    @ColumnInfo(name = "last_name")             var lastName: String,
    @ColumnInfo(name = "home_city")             var homeCity: String,
    @ColumnInfo(name = "email")                 var email: String,
    @ColumnInfo(name = "phone_number")          var phoneNumber: String,
    @ColumnInfo(name = "languages")             var languages: List<String>,
    @ColumnInfo(name = "isPublic")              var isPublic: Boolean,
    @ColumnInfo(name = "anim_company")          var isAnimatingInCompany: Boolean,
    @ColumnInfo(name = "anim_commercial")       var isAnimatingAsCommercial: Boolean
)

fun Profile.asDatabaseModel(): ProfileItem {
    return ProfileItem(
        profileId = this.profileId,
        firstName = this.firstName,
        lastName = this.lastName,
        homeCity = this.homeCity,
        email = this.email,
        phoneNumber = this.phoneNumber,
        languages = this.languages,
        isPublic = this.isPublic,
        isAnimatingInCompany = this.isAnimatingInCompany,
        isAnimatingAsCommercial = this.isAnimatingAsCommercial
    )
}

fun ProfileItem.asDomainModel(): Profile {
    return Profile(
        profileId = this.profileId,
        firstName = this.firstName,
        lastName = this.lastName,
        homeCity = this.homeCity,
        email = this.email,
        phoneNumber = this.phoneNumber,
        languages = this.languages,
        isPublic = this.isPublic,
        isAnimatingInCompany = this.isAnimatingInCompany,
        isAnimatingAsCommercial = this.isAnimatingAsCommercial
    )
}
