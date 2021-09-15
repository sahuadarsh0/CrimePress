package com.techipinfotech.allindiacrimepress.utils

enum class Constants(private val text: String) {

    URL("https://crimepress.allindiacrimepress.com/"),
    BASE_URL(URL.toString() + "tipilogin/"),
    HOME_URL(BASE_URL.toString() + "topics_material_approval/"),
    ASSETS_URL(URL.toString() + "uploads/tipilogin/"),
    STUDENT(ASSETS_URL.toString() + "student/");

    override fun toString(): String {
        return text
    }
}