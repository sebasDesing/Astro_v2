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
class AstroRepository @Inject constructor(
    private val api: AstroService,
    private val AstroTypeDao: AstroTypeDao,
    private val DailyImageDao: DailyImageDao,
    private val AstroDetailDao : AstroDetailDao
) {

    /* TIPOS DE ASTRO*/
    suspend fun getTypeAstroFromApi(): List<AstroType> {
        val response: List<AstroTypeModel> = api.getTypeAstro()
        return response.map {
            it.toDomain()
        }
    }

    suspend fun getTypeAstroFromDB(): List<AstroType> {
        val response: List<AstroTypeEntity> = AstroTypeDao.geAstroTypes()
        return response.map {
            it.toDomain()
        }
    }

    suspend fun insertAstroTypes(astroTypes: List<AstroTypeEntity>) {
        AstroTypeDao.insertAll(astroTypes)
    }

    suspend fun clearAstroTypes() {
        AstroTypeDao.deleteAllAstroTypes()
    }

    /* IMAGEN DEL DIA */
    suspend fun getDailyImageFromApi(): DailyImage {
        val response: Response<DailyImageModel> = api.getDailyImage()
        return response.body()?.toDomain() ?: throw Exception("Error fetching daily image")
    }
    suspend fun getDailyImageFromDB(): List<DailyImage> {
        val response: List<DailyImageEntity> = DailyImageDao.geDailyImage()
        return response.map {
            it.toDomain()
        }
    }
    suspend fun insertDailyImage(dailyI: List<DailyImageEntity>) {
    DailyImageDao.insertAll(dailyI)
    }

    suspend fun clearDailyImage() {
        DailyImageDao.deleteDailyImage()
    }


    /* DETALLES DE LOS ATROS*/
    suspend fun getAllAstrosDetailFromApi(): List<AstroDetail> {
        val response : List<AstroDetailModel> = api.getAllAstros()
        return  response.map {
            it.toDomain()
        }
    }
    suspend fun getAllAstrosDetailFromBD(): List<AstroDetail> {
        val response : List<AstroDetailEntity> = AstroDetailDao.getAstrosDetail()
        return  response.map {
            it.toDomain()
        }
    }

    suspend fun getAllAstrosDetailBytypeFromBD(id:Int): List<AstroDetail> {
        val response : List<AstroDetailEntity> = AstroDetailDao.getAstrosDetailByType(id)
        return  response.map {
            it.toDomain()
        }
    }



    suspend fun insertAstroDetail(detailA : List<AstroDetailEntity>){
        AstroDetailDao.insertAllDetailAstros(detailA)
    }
    suspend fun clearAstroDetail(){
        AstroDetailDao.deleteAllDetailAstros()
    }



}