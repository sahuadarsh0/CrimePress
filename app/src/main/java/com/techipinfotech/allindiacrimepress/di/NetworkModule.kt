package com.techipinfotech.allindiacrimepress.di

import android.content.Context
import android.util.Log
import com.techipinfotech.allindiacrimepress.data.remote.ApiService
import com.techipinfotech.allindiacrimepress.BuildConfig
import com.techipinfotech.allindiacrimepress.data.local.AppDatabase
import com.techipinfotech.allindiacrimepress.data.local.MembersDao
import com.techipinfotech.allindiacrimepress.data.remote.RemoteDataSource
import com.techipinfotech.allindiacrimepress.data.repository.MainRepository
import com.techipinfotech.allindiacrimepress.utils.Constants
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import kotlinx.coroutines.Dispatchers
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton
import kotlin.coroutines.CoroutineContext

@Module
@InstallIn(SingletonComponent::class)
object NetworkModule {

    @Provides
    @Singleton
    fun provideCoroutine(): CoroutineContext {
        return Dispatchers.IO
    }

    @Provides
    fun provideBaseUrl() = Constants.BASE_URL.toString()

    @Singleton
    @Provides
    fun provideOkHttpClient() = if (BuildConfig.DEBUG) {
        val loggingInterceptor = HttpLoggingInterceptor{ s: String? -> Log.d("asa", s!!) }
        loggingInterceptor.setLevel(HttpLoggingInterceptor.Level.BODY)
        OkHttpClient.Builder()
            .addInterceptor(loggingInterceptor)
            .build()
    } else {
        OkHttpClient
            .Builder()
            .build()
    }

    @Singleton
    @Provides
    fun provideConverterFactory(): GsonConverterFactory = GsonConverterFactory.create()

    @Singleton
    @Provides
    fun provideRetrofit(okHttpClient: OkHttpClient,gsonConverterFactory: GsonConverterFactory,BASE_URL : String) : Retrofit =
        Retrofit.Builder()
            .addConverterFactory(gsonConverterFactory)
            .baseUrl(BASE_URL)
            .client(okHttpClient)
            .build()


    @Provides
    @Singleton
    fun provideApiService(retrofit: Retrofit): ApiService = retrofit.create(ApiService::class.java)


    @Singleton
    @Provides
    fun provideRemoteDataSource(apiService: ApiService) = RemoteDataSource(apiService)


    @Singleton
    @Provides
    fun provideDatabase(@ApplicationContext appContext: Context) = AppDatabase.getDatabase(appContext)

    @Singleton
    @Provides
    fun provideCharacterDao(db: AppDatabase) = db.membersDao()

    @Singleton
    @Provides
    fun provideRepository(remoteDataSource: RemoteDataSource,localDataSource: MembersDao)
    = MainRepository(remoteDataSource,localDataSource)
}