package com.example.medicsapp.webservices.httpurlconnection

import android.util.Log
import com.google.gson.Gson
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import org.json.JSONObject
import java.io.OutputStreamWriter
import java.net.HttpURLConnection
import java.net.URL

class APIServices {

    /** Enum classes */
    enum class APIUrls {
        SignupPostData, SignInPostData
    }

    enum class ResponseCode(val responseCode: Int) {
        RESPONSE_OK(200),
        RESPONSE_FAILED(400),
    }

    /** Companion object */
    companion object {

        private const val baseUrl = "https://reqres.in/api/"

        /** Functions */
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

        fun authenticateUser(jsonObject: JSONObject, result: APIHttpResponder<SignInData, ErrorModel>) {
            httpRequest(
                SignInData::class.java,
                apiUrl(APIUrls.SignInPostData),
                urlMethod(APIUrls.SignInPostData),
                jsonObject,
                result
            )
        }
    }

}