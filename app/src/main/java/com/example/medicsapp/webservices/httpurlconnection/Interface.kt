package com.example.medicsapp.websercicess.retrofit

import com.example.medicsapp.webservices.httpurlconnection.*
import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.POST

/** Retrofit Interface */
interface RetrofitInterface {

    @POST(APIServices.APIEndPoints.register)
    fun postSignUpData(@Body postData: SignUpModelClass): Call<SignUpResponseData>

    @POST(APIServices.APIEndPoints.login)
    fun postSignInData(@Body postData: SignInModelClass): Call<SignInData>

}

/** Retrofit Interface Responder */
interface APIRetrofitResponder<SUCCESS, ERROR> {
    fun onSuccess(dataClass: SUCCESS?)
    fun onFailure(errorMessage: ErrorModel)
    fun onFailure(errorMessage: String)
}
