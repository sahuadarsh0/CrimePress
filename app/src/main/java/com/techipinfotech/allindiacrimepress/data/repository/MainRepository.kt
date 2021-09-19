package com.techipinfotech.allindiacrimepress.data.repository

import com.techipinfotech.allindiacrimepress.data.local.GalleryDao
import com.techipinfotech.allindiacrimepress.data.local.MembersDao
import com.techipinfotech.allindiacrimepress.data.remote.RemoteDataSource
import com.techipinfotech.allindiacrimepress.utils.performGetOperation
import javax.inject.Inject

class MainRepository @Inject constructor(
    private val remoteDataSource: RemoteDataSource,
    private val localDataSource: MembersDao,
    private val galleryDataSource: GalleryDao
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

    fun getGallery() = performGetOperation(
        databaseQuery = { galleryDataSource.getGallery() },
        networkCall = { remoteDataSource.getGallery() },
        saveCallResult = { galleryDataSource.insertAll(it) })

    suspend fun getBanner() = remoteDataSource.getBanner()
    suspend fun getMemberProfile(memberId: String) = remoteDataSource.getMemberProfile(memberId)
    suspend fun login(username: String, password: String) = remoteDataSource.login(username, password)

}