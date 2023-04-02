package com.example.astrop.data.database.entities

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.example.astrop.domain.model.DailyImage

@Entity(tableName = "dailyImage_table")
data class DailyImageEntity(
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "id") val id: Int = 0,
    @ColumnInfo(name = "date") val date: String,
    @ColumnInfo(name = "explanation") val explanation: String,
    @ColumnInfo(name = "hdurl") val hdurl: String,
    @ColumnInfo(name = "title") val title: String

)

fun  DailyImage.toDB() = DailyImageEntity(date=date, explanation = explanation, hdurl = hdurl, title = title)