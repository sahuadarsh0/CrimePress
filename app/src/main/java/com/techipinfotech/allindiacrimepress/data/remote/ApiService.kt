package com.techipinfotech.allindiacrimepress.data.remote

import com.techipinfotech.allindiacrimepress.model.MemberItem
import com.techipinfotech.allindiacrimepress.model.Members
import retrofit2.Response
import retrofit2.http.GET

interface ApiService {

    @GET("member/getmemberlist")
    suspend fun getMembersList(): Response<Members>

    @GET("member/getgallery")
    suspend fun getGallery(): Response<Members>

    @GET("member/getmembernotification")
    suspend fun getMemberNotification(): Response<Members>

    @GET("member/getmemberprofile")
    suspend fun getMemberProfile(): Response<MemberItem>

}