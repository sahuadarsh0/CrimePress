package com.techipinfotech.allindiacrimepress.ui.accounts

import androidx.lifecycle.ViewModel
import androidx.lifecycle.liveData
import com.techipinfotech.allindiacrimepress.data.repository.MainRepository
import com.techipinfotech.allindiacrimepress.utils.SharedPrefs
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class AccountsViewModel @Inject constructor(private val repository: MainRepository, private val sharedPrefs: SharedPrefs) :
    ViewModel() {

    val members = liveData { repository.getMemberProfile(sharedPrefs["member_id_temp"]!!).data?.let { emit(it[0]) } }
}