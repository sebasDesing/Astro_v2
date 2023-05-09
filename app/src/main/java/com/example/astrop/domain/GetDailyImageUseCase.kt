package com.example.astrop.domain

import com.example.astrop.data.AstroRepositoryImpl
import com.example.astrop.data.database.entities.toDB
import com.example.astrop.domain.model.DailyImage
import java.text.SimpleDateFormat
import java.util.*
import javax.inject.Inject

class GetDailyImageUseCase @Inject constructor(
    private val repository
    : AstroRepositoryImpl
) {

    suspend operator fun invoke(): List<DailyImage> {

        val dailyImage = repository.getDailyImageFromDB()
        return if (dailyImage.isNotEmpty() && dailyImage[0].date ==getCurrentDate()) {
            dailyImage
        } else {
            val apiDailyImage = repository.getDailyImageFromApi()
            repository.clearDailyImage()
            repository.insertDailyImage(listOf(apiDailyImage).map { it.toDB() })
            listOf(apiDailyImage)
        }

    }


    fun getCurrentDate(): String {
        val formatDate = SimpleDateFormat("yyyy-MM-dd", Locale.getDefault())
        val currentDate = Calendar.getInstance().time
        return formatDate.format(currentDate)
    }
}