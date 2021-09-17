package com.techipinfotech.allindiacrimepress.data.remote

import javax.inject.Inject

class RemoteDataSource @Inject constructor(private val apiService: ApiService) : BaseDataSource() {
    suspend fun getMembersList() = getResult { apiService.getMembersList() }
    suspend fun getGallery() = getResult { apiService.getGallery() }
    suspend fun getBanner() = getResult { apiService.getBanner() }
}