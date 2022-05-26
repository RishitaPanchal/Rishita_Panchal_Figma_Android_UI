package com.example.medicsapp.webservices.httpurlconnection

/** Interface HttpUrlConnection Responder */
interface APIHttpResponder<SUCCESS, ERROR> {
    fun onSuccess(dataClass: SUCCESS)
    fun onFailure(errorMessage: ErrorModel)
}

/** Modal classes*/
// Sign up modal classes
class SignUpModelClass (
    val name: String,
    val email: String,
    val password: String
)

data class SignUpResponseData(
    val id: Int,
    val token: String
)

// Sign in Model classes
data class SignInData(
    val token: String
)

class SignInModelClass (
    val email: String,
    val password: String
)

// Error Model class
data class ErrorModel(
    val error: String
)