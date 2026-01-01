package com.example.jokegenerator.data.local

import android.content.Context
import androidx.room.Room

object DatabaseProvider {

    @Volatile
    private var INSTANCE: AppDatabase? = null

    fun get(context: Context): AppDatabase {
        return INSTANCE ?: synchronized(this) {
            val db = Room.databaseBuilder(
                context.applicationContext,
                AppDatabase::class.java,
                "joke_generator.db"
            ).fallbackToDestructiveMigration()
                .build()
            INSTANCE = db
            db
        }
    }
}
