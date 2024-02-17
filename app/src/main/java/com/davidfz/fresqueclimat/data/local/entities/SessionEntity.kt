package com.davidfz.fresqueclimat.data.local.entities

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.davidfz.fresqueclimat.ui.explore.SessionItem


@Entity(tableName = "session")
class SessionEntity(
    @PrimaryKey(autoGenerate = true)            var sessionId: Long = 0L,
    @ColumnInfo(name = "date")                  var date: String,
    @ColumnInfo(name = "city")                  var city: String,
    @ColumnInfo(name = "country")               var country: String,
    @ColumnInfo(name = "total_tables")          var nbTables: Int,
    @ColumnInfo(name = "format")                var format: String,
    @ColumnInfo(name = "category")              var category: String,
    @ColumnInfo(name = "language")              var language: String,
    @ColumnInfo(name = "capacity_participants") var capacityParticipants: Int,
    @ColumnInfo(name = "capacity_facilitators") var capacityFacilitators: Int,
    @ColumnInfo(name = "total_participants")    var totalParticipants: Int,
    @ColumnInfo(name = "total_facilitators")    var totalFacilitators: Int,
    @ColumnInfo(name = "description")           var description: String,
    @ColumnInfo(name = "contact_name")          var contactName: String,
    @ColumnInfo(name = "contact_email")         var contactEmail: String
)

fun List<SessionEntity>.asDomainModel(): List<SessionItem> {
    return map {
        SessionItem(
            id = it.sessionId,
            date = it.date,
            city = it.city,
            country = it.country,
            language = it.language,
            format = it.format,
            price = 15.0,
            description = it.description,
            availableSlotsPublic = it.capacityParticipants - it.totalParticipants,
            totalParticipantsPublic = it.totalParticipants,
            capacitySlotsPublic = it.capacityParticipants,
            availableSlotsFacilitators = it.capacityFacilitators - it.totalFacilitators,
            totalFacilitators = it.totalFacilitators,
            capacitySlotsFacilitators = it.capacityFacilitators,
        )
    }
}
