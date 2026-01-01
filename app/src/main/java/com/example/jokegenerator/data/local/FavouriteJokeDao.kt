package com.example.jokegenerator.data.local

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import kotlinx.coroutines.flow.Flow

@Dao
interface FavouriteJokeDao {

    @Query("SELECT * FROM favourite_jokes ORDER BY createdAt DESC")
    fun getAll(): Flow<List<FavouriteJokeEntity>>

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insert(joke: FavouriteJokeEntity)

    @Delete
    suspend fun delete(joke: FavouriteJokeEntity)

    @Query("DELETE FROM favourite_jokes")
    suspend fun deleteAll()
}
