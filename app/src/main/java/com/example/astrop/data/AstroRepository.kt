package com.example.astrop.data

import com.example.astrop.data.database.entities.AstroDetailEntity
import com.example.astrop.data.database.entities.AstroTypeEntity
import com.example.astrop.data.database.entities.DailyImageEntity
import com.example.astrop.domain.model.AstroDetail
import com.example.astrop.domain.model.AstroType
import com.example.astrop.domain.model.DailyImage

interface AstroRepository {
    suspend fun getTypeAstroFromApi(): List<AstroType>

    suspend fun getTypeAstroFromDB(): List<AstroType>

    suspend fun insertAstroTypes(astroTypes: List<AstroTypeEntity>)

    suspend fun clearAstroTypes()

    /* IMAGEN DEL DIA */
    suspend fun getDailyImageFromApi(): DailyImage
    suspend fun getDailyImageFromDB(): List<DailyImage>
    suspend fun insertDailyImage(dailyI: List<DailyImageEntity>)

    suspend fun clearDailyImage()


    /* DETALLES DE LOS ATROS*/
    suspend fun getAllAstrosDetailFromApi(): List<AstroDetail>
    suspend fun getAllAstrosDetailFromBD(): List<AstroDetail>
    suspend fun getAllAstrosDetailBytypeFromBD(id: Int): List<AstroDetail>

    suspend fun insertAstroDetail(detailA: List<AstroDetailEntity>)
    suspend fun clearAstroDetail()

}