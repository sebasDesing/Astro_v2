package com.example.astrop.data.database

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.astrop.data.database.dao.AstroDetailDao
import com.example.astrop.data.database.dao.AstroTypeDao
import com.example.astrop.data.database.dao.DailyImageDao
import com.example.astrop.data.database.entities.AstroDetailEntity
import com.example.astrop.data.database.entities.AstroTypeEntity
import com.example.astrop.data.database.entities.DailyImageEntity

@Database(entities = [AstroTypeEntity::class, DailyImageEntity::class, AstroDetailEntity::class], version = 1)
abstract class Astrodb : RoomDatabase() {
    abstract fun getAstroTypeDao(): AstroTypeDao

    abstract fun getDailyImageDao(): DailyImageDao
    abstract fun getDetailAstros(): AstroDetailDao

}