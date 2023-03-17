package com.example.astrop.data.database

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.astrop.data.database.dao.AstroTypeDao
import com.example.astrop.data.database.entities.AstroTypeEntity

@Database(entities = [AstroTypeEntity::class], version = 1)
abstract class Astrodb : RoomDatabase() {
    abstract fun getAstroTypeDao(): AstroTypeDao
}