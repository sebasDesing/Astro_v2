package com.example.astrop.data.database.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.astrop.data.database.entities.DailyImageEntity

@Dao
interface DailyImageDao {
    @Query("SELECT * FROM dailyImage_table")
    suspend fun geDailyImage(): List<DailyImageEntity>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAll(dailyImage: List<DailyImageEntity>)

    @Query("DELETE FROM dailyImage_table")
    suspend fun deleteDailyImage()
}