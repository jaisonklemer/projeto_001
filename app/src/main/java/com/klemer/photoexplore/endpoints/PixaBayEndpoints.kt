package com.klemer.photoexplore.endpoints

import com.klemer.photoexplore.models.PixaBayResponse
import retrofit2.Call
import retrofit2.http.*

interface PixaBayEndpoints {

    @GET("api/")
    fun getLatestImages(
        @Query("key") apiKey: String,
        @Query("per_page") resultsCount: Int
    ): Call<PixaBayResponse>

    @GET("api/")
    fun searchImages(
        @Query("key") apiKey: String,
        @Query("q") query: String,
        @Query("per_page") resultsCount: Int
    ): Call<PixaBayResponse>
}