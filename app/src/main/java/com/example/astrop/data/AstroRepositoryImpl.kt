package com.example.astrop.data

import com.example.astrop.data.database.dao.AstroDetailDao
import com.example.astrop.data.database.dao.AstroTypeDao
import com.example.astrop.data.database.dao.DailyImageDao
import com.example.astrop.data.database.entities.AstroDetailEntity
import com.example.astrop.data.database.entities.AstroTypeEntity
import com.example.astrop.data.database.entities.DailyImageEntity
import com.example.astrop.data.model.AstroDetailModel
import com.example.astrop.data.model.AstroTypeModel
import com.example.astrop.data.model.DailyImageModel
import com.example.astrop.data.network.AstroService
import com.example.astrop.domain.model.AstroDetail
import com.example.astrop.domain.model.AstroType
import com.example.astrop.domain.model.DailyImage
import com.example.astrop.domain.model.toDomain
import retrofit2.Response
import javax.inject.Inject

/* SE ENCARGA DE DECIDIR DE DONDE TRAER LA INFORMACION*/
class AstroRepositoryImpl @Inject constructor(
    private val api: AstroService,
    private val AstroTypeDao: AstroTypeDao,
    private val DailyImageDao: DailyImageDao,
    private val AstroDetailDao: AstroDetailDao
) : AstroRepository {

    /* TIPOS DE ASTRO*/
    override suspend fun getTypeAstroFromApi(): List<AstroType> {
        val response: List<AstroTypeModel> = api.getTypeAstro()
        return response.map {
            it.toDomain()
        }
    }

    override suspend fun getTypeAstroFromDB(): List<AstroType> {
        val response: List<AstroTypeEntity> = AstroTypeDao.geAstroTypes()
        return response.map {
            it.toDomain()
        }
    }

    override suspend fun insertAstroTypes(astroTypes: List<AstroTypeEntity>) {
        AstroTypeDao.insertAll(astroTypes)
    }

    override suspend fun clearAstroTypes() {
        AstroTypeDao.deleteAllAstroTypes()
    }

    /* IMAGEN DEL DIA */
    override suspend fun getDailyImageFromApi(): DailyImage {
        val response: Response<DailyImageModel> = api.getDailyImage()
        return response.body()?.toDomain() ?: throw Exception("Error fetching daily image")
    }

    override suspend fun getDailyImageFromDB(): List<DailyImage> {
        val response: List<DailyImageEntity> = DailyImageDao.geDailyImage()
        return response.map {
            it.toDomain()
        }
    }

    override suspend fun insertDailyImage(dailyI: List<DailyImageEntity>) {
        DailyImageDao.insertAll(dailyI)
    }

    override suspend fun clearDailyImage() {
        DailyImageDao.deleteDailyImage()
    }


    /* DETALLES DE LOS ATROS*/
    override suspend fun getAllAstrosDetailFromApi(): List<AstroDetail> {
        val response: List<AstroDetailModel> = api.getAllAstros()
        return response.map {
            it.toDomain()
        }
    }

    override suspend fun getAllAstrosDetailFromBD(): List<AstroDetail> {
        val response: List<AstroDetailEntity> = AstroDetailDao.getAstrosDetail()
        return response.map {
            it.toDomain()
        }
    }

    override suspend fun getAllAstrosDetailBytypeFromBD(id: Int): List<AstroDetail> {
        val response: List<AstroDetailEntity> = AstroDetailDao.getAstrosDetailByType(id)
        return response.map {
            it.toDomain()
        }
    }


    override suspend fun insertAstroDetail(detailA: List<AstroDetailEntity>) {
        AstroDetailDao.insertAllDetailAstros(detailA)
    }

    override suspend fun clearAstroDetail() {
        AstroDetailDao.deleteAllDetailAstros()
    }


}