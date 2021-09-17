package com.techipinfotech.allindiacrimepress.model

import com.google.gson.annotations.SerializedName

class Notification: ArrayList<NotificationItem>()

data class NotificationItem(

    @SerializedName("nt_id")
    val ntId: String,

    @SerializedName("photo")
    val photo: String,

    @SerializedName("notifitacion")
    val notification: String,

    @SerializedName("despriction")
    val description: String
)
