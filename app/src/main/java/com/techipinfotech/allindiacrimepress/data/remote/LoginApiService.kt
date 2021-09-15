package com.techipinfotech.allindiacrimepress.data.remote

import com.techipinfotech.allindiacrimepress.model.MemberItem
import retrofit2.Response
import retrofit2.http.Field
import retrofit2.http.FormUrlEncoded
import retrofit2.http.POST

interface LoginApiService {


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