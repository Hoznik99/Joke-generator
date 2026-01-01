package com.example.jokegenerator.data.local

import androidx.room.Database
import androidx.room.RoomDatabase

@Database(
    entities = [FavouriteJokeEntity::class],
    version = 1
)
abstract class AppDatabase : RoomDatabase() {
    abstract fun favouriteJokeDao(): FavouriteJokeDao
}
