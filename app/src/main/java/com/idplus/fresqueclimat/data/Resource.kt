package com.idplus.fresqueclimat.data

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "resources")
class Resource(
    @PrimaryKey(autoGenerate = true)    var resourceId: Long = 0L,
    @ColumnInfo(name = "title")         var title: String?,
    @ColumnInfo(name = "description")   var description: String?,
    @ColumnInfo(name = "link")          var externalLink: String?
)