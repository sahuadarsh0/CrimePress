package com.techipinfotech.allindiacrimepress

import com.techipinfotech.allindiacrimepress.model.Members
import retrofit2.Response
import retrofit2.http.GET

interface ApiService {

    @GET("character")
    suspend fun getAllCharacters() : Response<Members>

}