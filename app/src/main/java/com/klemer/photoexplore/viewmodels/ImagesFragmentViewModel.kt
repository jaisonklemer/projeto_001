package com.klemer.photoexplore.viewmodels

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.klemer.photoexplore.models.PixaBayImage
import com.klemer.photoexplore.models.PixaBayResponse
import com.klemer.photoexplore.repository.ImagesRepository
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class ImagesFragmentViewModel : ViewModel(), Callback<PixaBayResponse> {

    private val _images = MutableLiveData<List<PixaBayImage>>()

    val images: LiveData<List<PixaBayImage>> = _images

    val repository = ImagesRepository()

    fun getLastImages() {
        repository.getLatestImages(this)
    }

    fun searchImages(query: String) {
        repository.searchImages(query, this)
    }

    override fun onResponse(call: Call<PixaBayResponse>, response: Response<PixaBayResponse>) {
        if (response.code() == 200)
            this._images.value = response.body()!!.images
    }

    override fun onFailure(call: Call<PixaBayResponse>, t: Throwable) {
        TODO("Not yet implemented")
    }

}