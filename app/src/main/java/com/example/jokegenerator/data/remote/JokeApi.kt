package com.example.jokegenerator.data.remote

import retrofit2.http.GET
import retrofit2.http.Query

interface JokeApi {
    @GET("joke/Any")
    suspend fun getRandomJoke(
        @Query("blacklistFlags") blacklistFlags: String = "nsfw,religious,political,racist,sexist,explicit"
    ): JokeDto
}
