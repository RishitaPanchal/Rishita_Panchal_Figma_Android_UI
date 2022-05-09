package com.example.medicsapp.webservices.httpurlconnection

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

open class BaseViewModel: ViewModel() {

    /** Instance variables */
    private val mIsLoading = MutableLiveData<Boolean>()
    val isLoading: LiveData<Boolean>
        get() = mIsLoading

    private val mOnFailure = MutableLiveData<String>()
    val onFailure: LiveData<String>
        get() = mOnFailure

    /** Functions */
    fun initLoading(value: Boolean) {
        mIsLoading.value = value
    }

    fun failureMessage(message: String) {
        mOnFailure.value = message
    }

}