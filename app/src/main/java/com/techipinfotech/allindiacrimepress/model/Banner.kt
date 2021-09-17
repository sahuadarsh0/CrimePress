package com.techipinfotech.allindiacrimepress.model


import com.google.gson.annotations.SerializedName

class Banner : ArrayList<BannerItem>()

data class BannerItem(
    @SerializedName("banner_id")
    val bannerId: String,
    @SerializedName("photo")
    val photo: String,
    @SerializedName("title")
    val title: String
)