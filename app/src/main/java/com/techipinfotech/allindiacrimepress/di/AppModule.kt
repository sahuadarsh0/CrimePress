package com.techipinfotech.allindiacrimepress.di

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import kotlinx.coroutines.Dispatchers
import javax.inject.Singleton
import kotlin.coroutines.CoroutineContext


@Module
@InstallIn(SingletonComponent::class)
class AppModule {

    @Provides
    @Singleton
    fun provideCoroutine(): CoroutineContext {
        return Dispatchers.IO
    }

//    @Provides
//    @Singleton
//    fun provideNetwork(@ApplicationContext context: Context): NetworkConnectivity {
//        return Network(context)
//    }
}