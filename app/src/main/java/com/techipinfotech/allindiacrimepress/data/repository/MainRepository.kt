package com.techipinfotech.allindiacrimepress.data.repository

import com.techipinfotech.allindiacrimepress.data.local.MembersDao
import com.techipinfotech.allindiacrimepress.data.remote.RemoteDataSource
import com.techipinfotech.allindiacrimepress.utils.performGetOperation
import javax.inject.Inject

class MainRepository @Inject constructor(
    private val remoteDataSource: RemoteDataSource,
    private val localDataSource: MembersDao
) {
    fun getMembersList() = performGetOperation(
        databaseQuery = { localDataSource.getMembersList() },
        networkCall = { remoteDataSource.getMembersList() },
        saveCallResult = { localDataSource.insertAll(it) })

    fun getMyMembersList(memberId: String) = performGetOperation(
        databaseQuery = { localDataSource.getMembersList() },
        networkCall = { remoteDataSource.getMyMembersList(memberId) },
        saveCallResult = {
            localDataSource.clearAll()
            localDataSource.insertAll(it)
        })

    suspend fun getGallery() = remoteDataSource.getGallery()
    suspend fun getBanner() = remoteDataSource.getBanner()

}