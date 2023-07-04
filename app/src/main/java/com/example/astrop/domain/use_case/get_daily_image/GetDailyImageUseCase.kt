package com.example.astrop.domain.use_case.get_daily_image

import com.example.astrop.domain.model.DailyImage

interface GetDailyImageUseCase {

    suspend operator fun  invoke(): List<DailyImage>
}