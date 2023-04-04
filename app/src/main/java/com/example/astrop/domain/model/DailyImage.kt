package com.example.astrop.domain.model

import com.example.astrop.data.database.entities.DailyImageEntity
import com.example.astrop.data.model.DailyImageModel

data class DailyImage(
    val date: String,
    val explanation: String,
    val hdurl: String,
    val title: String
)

fun DailyImageModel.toDomain() =
    DailyImage(date, explanation, hdurl, title)

fun DailyImageEntity.toDomain() = DailyImage(date, explanation, hdurl, title)

