package com.techipinfotech.allindiacrimepress.ui.list

import androidx.lifecycle.ViewModel
import com.techipinfotech.allindiacrimepress.data.repository.MainRepository
import com.techipinfotech.allindiacrimepress.utils.SharedPrefs
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class ListViewModel @Inject constructor(private val repository: MainRepository, private val sharedPrefs: SharedPrefs) :
    ViewModel() {

    val members = repository.getMyMembersList(sharedPrefs["member_id"]!!)
}