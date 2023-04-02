package com.example.astrop.domain

import com.example.astrop.data.AstroRepository
import com.example.astrop.data.database.entities.toDB
import com.example.astrop.domain.model.DailyImage
import javax.inject.Inject

class GetDailyImageUseCase @Inject constructor(
    private val repository
    : AstroRepository
) {
    suspend operator fun invoke(): List<DailyImage> {

        val dailyImage = repository.getDailyImageFromDB()
        return if (dailyImage.isNotEmpty()) {
            dailyImage
        } else {
            val apiDailyImage = repository.getDailyImageFromApi()
            repository.clearDailyImage()
            repository.insertDailyImage(listOf(apiDailyImage).map { it.toDB() })
            listOf(apiDailyImage)
        }

    }

}