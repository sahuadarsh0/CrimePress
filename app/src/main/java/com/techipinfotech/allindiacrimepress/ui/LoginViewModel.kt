package com.techipinfotech.allindiacrimepress.ui

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.liveData
import androidx.lifecycle.viewModelScope
import com.techipinfotech.allindiacrimepress.data.repository.MainRepository
import com.techipinfotech.allindiacrimepress.model.MemberItem
import com.techipinfotech.allindiacrimepress.utils.Resource
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class LoginViewModel @Inject constructor(private val repository: MainRepository) : ViewModel() {

    //memberItem
    val memberItem : MutableLiveData<Resource<MemberItem>> = MutableLiveData()

    fun login(username: String,password : String) = viewModelScope.launch {
       repository.login(username,password)
        memberItem.postValue(repository.login(username,password))
    }

}