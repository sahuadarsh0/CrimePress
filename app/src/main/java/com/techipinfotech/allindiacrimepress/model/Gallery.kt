package com.techipinfotech.allindiacrimepress.model


import com.google.gson.annotations.SerializedName

class Gallery : ArrayList<GalleryItem>()

data class GalleryItem(
    @SerializedName("gallery_id")
    val galleryId: String,
    @SerializedName("photo")
    val photo: String,
    @SerializedName("title")
    val title: String
)