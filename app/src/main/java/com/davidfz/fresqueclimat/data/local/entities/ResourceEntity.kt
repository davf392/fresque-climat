package com.davidfz.fresqueclimat.data.local.entities

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "resources")
class ResourceEntity(
    @PrimaryKey(autoGenerate = true)    var resourceId: Long = 0L,
    @ColumnInfo(name = "title")         var title: String?,
    @ColumnInfo(name = "description")   var description: String?,
    @ColumnInfo(name = "link")          var externalLink: String?
)