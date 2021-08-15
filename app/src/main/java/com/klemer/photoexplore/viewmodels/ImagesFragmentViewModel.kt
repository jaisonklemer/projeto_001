package com.klemer.photoexplore.viewmodels

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.klemer.photoexplore.models.PixaBayImage

class ImagesFragmentViewModel : ViewModel() {

    private val _images = MutableLiveData<List<PixaBayImage>>()

    val images: LiveData<List<PixaBayImage>> = _images

    fun updateImages(images: List<PixaBayImage>) {
        this._images.value = images
    }
}