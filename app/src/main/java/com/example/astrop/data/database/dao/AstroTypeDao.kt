package com.example.astrop.data.database.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.astrop.data.database.entities.AstroTypeEntity

@Dao
interface AstroTypeDao {
    @Query("SELECT * FROM astroType_table ")
    suspend fun geAstroTypes(): List<AstroTypeEntity>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAll(astros: List<AstroTypeEntity>)

    @Query("DELETE FROM astroType_table")
    suspend fun deleteAllAstroTypes()
}