package com.klemer.photoexplore.interfaces

import com.klemer.photoexplore.models.PixaBayImage

interface ImageClickListener {
    fun onImageClick(image : PixaBayImage)
}