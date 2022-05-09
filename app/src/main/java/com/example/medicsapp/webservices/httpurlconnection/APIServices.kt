package com.example.medicsapp.webservices.httpurlconnection

import android.util.Log
import com.example.medicsapp.websercicess.retrofit.APIRetrofitResponder
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import org.json.JSONObject
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.io.OutputStreamWriter
import java.net.HttpURLConnection
import java.net.URL

var APICallingType = APIServices.RetrofitOrHttpUrlConnection.Retrofit.name

class APIServices {

    object APIEndPoints {
        const val register = "register"
        const val login = "login"
    }

    enum class APIUrls {
        SignupPostData, SignInPostData
    }

    enum class ResponseCode(val responseCode: Int) {
        RESPONSE_OK(200),
        RESPONSE_FAILED(400)
    }

    enum class RetrofitOrHttpUrlConnection {
        Retrofit, HttpUrlConnection
    }

    companion object {

        const val baseUrl = "https://reqres.in/api/"

        private fun urlMethod(task: APIUrls): String {
            return when (task) {
                APIUrls.SignupPostData -> "POST"
                APIUrls.SignInPostData -> "POST"
            }
        }

        private fun apiUrl(task: APIUrls): String {
            return when (task) {
                APIUrls.SignupPostData -> "${baseUrl}register"
                APIUrls.SignInPostData -> "${baseUrl}login"
            }
        }

        private fun <T> httpRequest(
            dataClass: Class<T>,
            stringUrl: String,
            inputMethod: String,
            credentials: JSONObject,
            result: APIHttpResponder<T, ErrorModel>,
        ) {
            val url = URL(stringUrl)
            CoroutineScope(Dispatchers.IO).launch {
                runCatching {
                    with(url.openConnection() as HttpURLConnection) {
                        requestMethod = inputMethod
                        setRequestProperty("content-type", "application/json")
                        val wr = OutputStreamWriter(outputStream)
                        wr.write(credentials.toString())
                        wr.flush()
                        when (responseCode) {
                            ResponseCode.RESPONSE_OK.responseCode -> {
                                val response = inputStream.bufferedReader()
                                val readResponse = response.readText()
                                val jsonResponse = Gson().fromJson(readResponse, dataClass)
                                CoroutineScope(Dispatchers.Main).launch {
                                    result.onSuccess(jsonResponse)
                                }
                            }
                            ResponseCode.RESPONSE_FAILED.responseCode -> {
                                val response = errorStream.bufferedReader()
                                val readResponse = response.readText()
                                val jsonResponse =
                                    Gson().fromJson(readResponse, ErrorModel::class.java)
                                Log.d("error", readResponse)
                                CoroutineScope(Dispatchers.Main).launch {
                                    result.onFailure(jsonResponse)
                                }
                            }
                            else -> {
                                Log.d("Unknown code", "$responseCode")
                            }
                        }
                    }
                }
            }
        }

        private fun <T> retrofitEnqueue(
            call: Call<T>,
            result: APIRetrofitResponder<T, ErrorModel>
        ) {
            val type = object : TypeToken<ErrorModel>() {}.type
            call.enqueue(object : Callback<T> {
                override fun onResponse(call: Call<T>, response: Response<T>) {
                    when(response.code()) {
                        ResponseCode.RESPONSE_OK.responseCode -> result.onSuccess(response.body())
                        ResponseCode.RESPONSE_FAILED.responseCode -> {
                            response.errorBody().let {
                                val errorResponse: ErrorModel =
                                    Gson().fromJson(response.errorBody()?.charStream(), type)
                                result.onFailure(errorResponse)
                            }
                        }
                    }
                }
                override fun onFailure(call: Call<T>, t: Throwable) {
                    result.onFailure("Something went wrong!")
                }
            })
        }

        fun createUserHttp(
            jsonObject: JSONObject,
            resultHttp: APIHttpResponder<SignUpResponseData, ErrorModel>
        ) {
            httpRequest(
                SignUpResponseData::class.java,
                apiUrl(APIUrls.SignupPostData),
                urlMethod(APIUrls.SignupPostData),
                jsonObject,
                resultHttp
            )
        }

        fun createUserRetrofit(
            signUpModelClass: SignUpModelClass,
            result: APIRetrofitResponder<SignUpResponseData, ErrorModel>
        ) {
            retrofitEnqueue(
                APIRetrofitClient.retrofitBuilder.postSignUpData(signUpModelClass),
                result
            )
        }

        fun authenticateUser(jsonObject: JSONObject, result: APIHttpResponder<SignInData, ErrorModel>) {
            httpRequest(
                SignInData::class.java,
                apiUrl(APIUrls.SignInPostData),
                urlMethod(APIUrls.SignInPostData),
                jsonObject,
                result
            )
        }

        fun authenticateUserRetrofit(
            signInModelClass: SignInModelClass,
            result: APIRetrofitResponder<SignInData, ErrorModel>
        ) {
            retrofitEnqueue(
                APIRetrofitClient.retrofitBuilder.postSignInData(signInModelClass),
                result
            )
        }
    }

}