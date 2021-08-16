package com.klemer.photoexplore.viewmodels

import android.os.Bundle
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.klemer.photoexplore.models.PixaBayImage

class ImageDetailViewModel : ViewModel() {

    private val _image = MutableLiveData<PixaBayImage>()

    val image: LiveData<PixaBayImage> = _image

    fun treatArguments(arguments: Bundle?) {
        (arguments?.getSerializable("image") as PixaBayImage).apply {
            _image.value = this
        }

    }
}