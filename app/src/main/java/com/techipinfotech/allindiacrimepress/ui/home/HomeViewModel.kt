package com.techipinfotech.allindiacrimepress.ui.home

import androidx.lifecycle.ViewModel
import androidx.lifecycle.liveData
import com.techipinfotech.allindiacrimepress.data.repository.MainRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(private val repository: MainRepository) : ViewModel() {


    val members = repository.getMembersList()
    val banner = liveData { emit(repository.getBanner()) }

}