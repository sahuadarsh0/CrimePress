package com.techipinfotech.allindiacrimepress.data.remote

import com.techipinfotech.allindiacrimepress.model.Members
import retrofit2.Response
import retrofit2.http.GET

interface ApiService {

    @GET("/member/getmemberlist")
    suspend fun getMembersList(): Response<Members>

}