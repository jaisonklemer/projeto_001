package com.klemer.photoexplore.endpoints

import com.klemer.photoexplore.models.PixaBayResponse
import retrofit2.Call
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

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