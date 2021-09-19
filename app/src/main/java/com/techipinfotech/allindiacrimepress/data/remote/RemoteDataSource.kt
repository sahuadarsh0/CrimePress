package com.techipinfotech.allindiacrimepress.data.remote

import javax.inject.Inject

class RemoteDataSource @Inject constructor(private val apiService: ApiService) : BaseDataSource() {
    suspend fun getMembersList() = getResult { apiService.getMembersList() }
    suspend fun getMyMembersList(memberId: String) = getResult { apiService.getMyMembersList(memberId) }
    suspend fun getGallery() = getResult { apiService.getGallery() }
    suspend fun getBanner() = getResult { apiService.getBanner() }
    suspend fun getMemberProfile(memberId: String) = getResult { apiService.getMemberProfile(memberId) }
    suspend fun login(username: String, password: String) = getResult { apiService.login(username, password) }
}