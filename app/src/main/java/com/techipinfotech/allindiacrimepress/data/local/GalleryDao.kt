package com.techipinfotech.allindiacrimepress.data.local

import androidx.lifecycle.LiveData
import androidx.room.*
import com.techipinfotech.allindiacrimepress.model.Gallery
import com.techipinfotech.allindiacrimepress.model.GalleryItem
import com.techipinfotech.allindiacrimepress.model.MemberItem
import com.techipinfotech.allindiacrimepress.model.Members

@Dao
interface GalleryDao {

    @Query("SELECT * FROM gallery")
    fun getGallery() : LiveData<List<GalleryItem>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAll(gallery: Gallery)


    @Query("DELETE FROM gallery")
    suspend fun clearAll()


}