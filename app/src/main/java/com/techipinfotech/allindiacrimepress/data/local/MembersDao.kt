package com.techipinfotech.allindiacrimepress.data.local

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.techipinfotech.allindiacrimepress.model.MemberItem
import com.techipinfotech.allindiacrimepress.model.Members

@Dao
interface MembersDao {

    @Query("SELECT * FROM members")
    fun getMembersList() : LiveData<List<MemberItem>>

    @Query("SELECT * FROM members WHERE id = :id")
    fun getMember(id: String): LiveData<MemberItem>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAll(members: Members)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(memberItem: MemberItem)


}