package com.example.medicsapp.webservices.httpurlconnection

import androidx.lifecycle.*
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import org.json.JSONObject

class SignInSignUpViewModel : BaseViewModel() {

    /** Instance variable */
    private val mSucceedCreateUser = MutableLiveData<SignUpResponseData?>()
    val succeedCreateUser: LiveData<SignUpResponseData?>
        get() = mSucceedCreateUser

    private val mSucceedAuthenticateUser = MutableLiveData<SignInData?>()
    val succeedAuthenticateUser: LiveData<SignInData?>
        get() = mSucceedAuthenticateUser

    /** Functions */
    fun createUserByHttp(jsonObject: JSONObject) {
        viewModelScope.launch {
            initLoading(true)
            APIServices.createUserHttp(jsonObject, object : APIHttpResponder<SignUpResponseData, ErrorModel> {
                override fun onSuccess(dataClass: SignUpResponseData) {
                    mSucceedCreateUser.value = dataClass
                    initLoading(false)
                }
                override fun onFailure(errorMessage: ErrorModel) {
                    failureMessage(errorMessage.error)
                    initLoading(false)
                }
            })
        }
    }

    fun authenticateUserByHttp(jsonObject: JSONObject) {
        initLoading(true)
        viewModelScope.launch {
            delay(2000)
            APIServices.authenticateUser(
                jsonObject,
                object : APIHttpResponder<SignInData, ErrorModel> {
                    override fun onSuccess(dataClass: SignInData) {
                        mSucceedAuthenticateUser.value = dataClass
                        initLoading(false)
                    }
                    override fun onFailure(errorMessage: ErrorModel) {
                        failureMessage(errorMessage.error)
                        initLoading(false)
                    }
                })
        }
    }
}