package com.techipinfotech.allindiacrimepress.data.remote

import com.techipinfotech.allindiacrimepress.model.*
import retrofit2.Response
import retrofit2.http.Field
import retrofit2.http.FormUrlEncoded
import retrofit2.http.GET
import retrofit2.http.POST

interface ApiService {

    @GET("member/getmemberlist")
    suspend fun getMembersList(): Response<Members>

    @GET("member/getbanner")
    suspend fun getBanner(): Response<Banner>

    @GET("member/getgallery")
    suspend fun getGallery(): Response<Gallery>

    @GET("member/getmembernotification")
    suspend fun getMemberNotification(): Response<Notification>

    @GET("member/getmemberprofile")
    suspend fun getMemberProfile(): Response<MemberItem>

    @FormUrlEncoded
    @POST("member/memberlogin")
    suspend fun login(
        @Field("user_name") user_name: String?,
        @Field("password") password: String?
    ): Response<MemberItem?>?

    @FormUrlEncoded
    @POST("changepassword")
    fun changePassword(
        @Field("user_name") user_name: String?,
        @Field("user_password") password: String?
    ): Response<MemberItem?>?

}