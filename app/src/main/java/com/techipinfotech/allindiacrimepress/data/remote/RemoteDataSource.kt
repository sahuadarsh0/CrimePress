package com.techipinfotech.allindiacrimepress.data.remote

import javax.inject.Inject

class RemoteDataSource @Inject constructor(private val apiService: ApiService) : BaseDataSource() {
    suspend fun getMembersList() = getResult { apiService.getMembersList() }
}