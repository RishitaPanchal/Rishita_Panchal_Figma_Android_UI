package com.example.medicsapp.webservices.httpurlconnection

import com.example.medicsapp.websercicess.retrofit.RetrofitInterface
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

/** Singleton object */
class APIRetrofitClient {

    companion object {
        val retrofitBuilder: RetrofitInterface by lazy {
            return@lazy Retrofit.Builder().baseUrl(APIServices.baseUrl)
                .addConverterFactory(GsonConverterFactory.create())
                .build()
                .create(RetrofitInterface::class.java)
        }
    }

}
