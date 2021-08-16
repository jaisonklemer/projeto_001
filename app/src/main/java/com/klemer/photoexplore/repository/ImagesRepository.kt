package com.klemer.photoexplore.repository

import com.klemer.photoexplore.BuildConfig
import com.klemer.photoexplore.endpoints.PixaBayEndpoints
import com.klemer.photoexplore.models.PixaBayResponse
import com.klemer.photoexplore.services.RetrofitService
import retrofit2.Callback

class ImagesRepository {

    private val pixabay by lazy {
        RetrofitService()
            .getInstance(BuildConfig.PIXABAY_API_URL)
            .create(PixaBayEndpoints::class.java)
    }

    fun getLatestImages(callback: Callback<PixaBayResponse>) {
        pixabay.getLatestImages(BuildConfig.PIXABAY_API_KEY, 200)
            .clone()
            .enqueue(callback)
    }

    fun searchImages(query: String, callback: Callback<PixaBayResponse>) {
        val searchQuery = query.replace(" ", "+")

        pixabay.searchImages(BuildConfig.PIXABAY_API_KEY, searchQuery, 200)
            .clone()
            .enqueue(callback)
    }
}