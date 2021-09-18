package com.techipinfotech.allindiacrimepress.model


import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName

class Gallery : ArrayList<GalleryItem>()

@Entity(tableName = "gallery")
data class GalleryItem(
    @PrimaryKey
    @SerializedName("gallery_id")
    val galleryId: String,
    @SerializedName("photo")
    val photo: String,
    @SerializedName("title")
    val title: String
)