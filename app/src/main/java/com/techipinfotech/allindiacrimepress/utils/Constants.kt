package com.techipinfotech.allindiacrimepress.utils

enum class Constants(private val text: String) {

    URL("https://aicpnews.in/"),
    BASE_URL(URL.toString() + "tipilogin/"),

    ASSETS_URL(URL.toString() + "uploads/tipilogin/"),
    BANNER(ASSETS_URL.toString() + "app_home_slider/"),
    GALLERY(ASSETS_URL.toString() + "app_gallery_image/"),
    PHOTO(ASSETS_URL.toString() + "photo/");

    override fun toString(): String {
        return text
    }
}