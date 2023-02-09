package com.idplus.fresqueclimat.data.local.entities

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey


@Entity(tableName = "session")
class Session(
    @PrimaryKey(autoGenerate = true)            var sessionId: Long = 0L,
    @ColumnInfo(name = "date")                  var date: String?,
    @ColumnInfo(name = "city")                  var city: String?,
    @ColumnInfo(name = "country")               var country: String?,
    @ColumnInfo(name = "total_tables")          var nbTables: Int?,
    @ColumnInfo(name = "format")                var format: String?,
    @ColumnInfo(name = "category")              var category: String?,
    @ColumnInfo(name = "language")              var language: String?,
    @ColumnInfo(name = "capacity_participants") var capacityParticipants: Int?,
    @ColumnInfo(name = "capacity_facilitators") var capacityFacilitators: Int?,
    @ColumnInfo(name = "total_participants")    var totalParticipants: Int?,
    @ColumnInfo(name = "total_facilitators")    var totalFacilitators: Int?,
    @ColumnInfo(name = "description")           var description: String?,
    @ColumnInfo(name = "contact_name")          var contactName: String?,
    @ColumnInfo(name = "contact_email")         var contactEmail: String?
)