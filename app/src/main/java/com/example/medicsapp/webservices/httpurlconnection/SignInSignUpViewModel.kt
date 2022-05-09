package com.example.medicsapp.webservices.httpurlconnection

import androidx.lifecycle.*
import com.example.medicsapp.websercicess.retrofit.APIRetrofitResponder
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import org.json.JSONObject

class SignInSignUpViewModel : BaseViewModel() {

    /** Instance variables */
    private val mSucceedCreateUser = MutableLiveData<SignUpResponseData?>()
    val succeedCreateUser: LiveData<SignUpResponseData?>
        get() = mSucceedCreateUser

    private val mSucceedAuthenticateUser = MutableLiveData<SignInData?>()
    val succeedAuthenticateUser: LiveData<SignInData?>
        get() = mSucceedAuthenticateUser

    /** Functions */
    private fun createUserByHttp(jsonObject: JSONObject) {
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

    private fun createUserByRetrofit(signUpModelClass: SignUpModelClass) {
        initLoading(true)
        APIServices.createUserRetrofit(signUpModelClass, object : APIRetrofitResponder<SignUpResponseData, ErrorModel> {
            override fun onSuccess(dataClass: SignUpResponseData?) {
                mSucceedCreateUser.value = dataClass
                initLoading(false)
            }
            override fun onFailure(errorMessage: ErrorModel) {
                failureMessage(errorMessage.error)
                initLoading(false)
            }
            override fun onFailure(errorMessage: String) {
                failureMessage("Oops! something went wrong!")
                initLoading(false)
            }
        })
    }

    fun selectCreateUserAPICallingType(choice: String, name: String, email: String, password: String) {
        when(choice) {
            APIServices.RetrofitOrHttpUrlConnection.Retrofit.name -> createUserByRetrofit(
                SignUpModelClass(name, email, password)
            )
            APIServices.RetrofitOrHttpUrlConnection.HttpUrlConnection.name -> {
                val credentials = JSONObject()
                credentials.put("email", email)
                credentials.put("password", password)
                createUserByHttp(credentials)
            }
        }
    }

    private fun authenticateUser(jsonObject: JSONObject) {
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

    private fun authenticateUserByRetrofit(signInModelClass: SignInModelClass) {
        initLoading(true)
        APIServices.authenticateUserRetrofit(signInModelClass, object : APIRetrofitResponder<SignInData, ErrorModel> {
            override fun onSuccess(dataClass: SignInData?) {
                mSucceedAuthenticateUser.value = dataClass
                initLoading(false)
            }

            override fun onFailure(errorMessage: ErrorModel) {
                failureMessage(errorMessage.error)
                initLoading(false)
            }

            override fun onFailure(errorMessage: String) {
                failureMessage("Oops!! Something went wrong...")
                initLoading(false)
            }
        })
    }

    fun selectAuthenticateAPICallingType(choice: String, email:String, password: String) {
        when(choice) {
            APIServices.RetrofitOrHttpUrlConnection.HttpUrlConnection.name -> {
                val credentials = JSONObject()
                credentials.put("email", email)
                credentials.put("password", password)
                authenticateUser(credentials)
            }
            APIServices.RetrofitOrHttpUrlConnection.Retrofit.name -> {
                authenticateUserByRetrofit(SignInModelClass(email, password))
            }
        }
    }

}