package com.lbg.techassest.framework.database

import androidx.room.Database
import androidx.room.RoomDatabase
import com.lbg.techassest.domain.local.FavoriteMoviesEntity
import com.lbg.techassest.domain.local.FavoriteMoviesEntityDao

@Database(entities = [FavoriteMoviesEntity::class], version = 1)
abstract class AppDatabase : RoomDatabase() {
    abstract fun favoriteMoviesDao(): FavoriteMoviesEntityDao
}