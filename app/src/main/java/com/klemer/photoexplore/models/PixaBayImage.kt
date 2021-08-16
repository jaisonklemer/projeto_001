package com.klemer.photoexplore.models

import com.google.gson.annotations.SerializedName
import java.io.Serializable

data class PixaBayResponse(
    @SerializedName("hits")
    val images: List<PixaBayImage>
)

data class PixaBayImage(
    @SerializedName("id")
    val id: Int,

    @SerializedName("type")
    val type: String,

    @SerializedName("previewURL")
    val previewImage: String,

    @SerializedName("largeImageURL")
    val largeImage: String,

    @SerializedName("webformatURL")
    val webFormatImage: String,

    @SerializedName("userImageURL")
    val userAvatar: String,

    @SerializedName("user")
    val userName: String,

    @SerializedName("downloads")
    val downloadsCount: String,

    @SerializedName("views")
    val viewsCount: String,

    @SerializedName("imageWidth")
    val imageWidth: String,

    @SerializedName("imageHeight")
    val imageHeight: String,


    ) : Serializable
