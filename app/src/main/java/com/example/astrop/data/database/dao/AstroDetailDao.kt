package com.example.astrop.data.database.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.astrop.data.database.entities.AstroDetailEntity


@Dao
interface AstroDetailDao {

    @Query("SELECT * FROM astro_table")
    suspend fun getAstrosDetail(): List<AstroDetailEntity>


    @Query("SELECT * FROM astro_table WHERE id_type_astro=:astroTypeId")
    suspend fun getAstrosDetailByType(astroTypeId: Int): List<AstroDetailEntity>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAllDetailAstros(astros: List<AstroDetailEntity>)

    @Query("DELETE FROM astro_table")
    suspend fun deleteAllDetailAstros()
}