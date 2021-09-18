package com.techipinfotech.allindiacrimepress.data.local

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.techipinfotech.allindiacrimepress.model.GalleryItem
import com.techipinfotech.allindiacrimepress.model.MemberItem


@Database(entities = [MemberItem::class, GalleryItem::class], version = 1, exportSchema = false)
abstract class AppDatabase : RoomDatabase() {

    abstract fun membersDao(): MembersDao
    abstract fun galleryDao(): GalleryDao

    companion object {
        @Volatile
        private var instance: AppDatabase? = null

        fun getDatabase(context: Context): AppDatabase =
            instance ?: synchronized(this) { instance ?: buildDatabase(context).also { instance = it } }

        private fun buildDatabase(appContext: Context) =
            Room.databaseBuilder(appContext, AppDatabase::class.java, "AICP")
                .fallbackToDestructiveMigration()
                .build()
    }

}