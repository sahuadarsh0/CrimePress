package com.techipinfotech.allindiacrimepress.data.remote

import com.techipinfotech.allindiacrimepress.model.*
import retrofit2.Response
import retrofit2.http.*

interface ApiService {

    @GET("member/getmemberlist")
    suspend fun getMembersList(): Response<Members>

    @GET("member/getbanner")
    suspend fun getBanner(): Response<Banner>

    @GET("member/getgallery")
    suspend fun getGallery(): Response<Gallery>

    @GET("member/getmembernotification")
    suspend fun getMemberNotification(): Response<Notification>

    @GET("member/getmemberprofile/{member_id}")
    suspend fun getMyProfile(@Path("member_id") member_id: String): Response<Members>

    @GET("member/getmymember/{member_id}")
    suspend fun getMyMembersList(@Path("member_id") member_id: String): Response<Members>

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