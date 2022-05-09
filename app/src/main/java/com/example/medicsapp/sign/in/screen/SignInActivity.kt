package com.example.medicsapp.sign.`in`.screen

import android.content.Intent
import android.graphics.Color
import android.os.Bundle
import android.text.*
import android.text.method.LinkMovementMethod
import android.view.View
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.core.view.isVisible
import androidx.lifecycle.ViewModelProvider
import com.example.medicsapp.R
import com.example.medicsapp.databinding.ActivitySignInBinding
import com.example.medicsapp.forgot.password.screen.CreatePasswordActivity
import com.example.medicsapp.forgot.password.screen.ForgotPasswordActivity
import com.example.medicsapp.home.screen.ui.BottomNavigationActivity
import com.example.medicsapp.signn.up.screen.SignUpActivity
import com.example.medicsapp.webservices.httpurlconnection.SignInSignUpViewModel
import com.google.android.material.textfield.TextInputLayout
import org.json.JSONObject

class SignInActivity : BaseActivity(), View.OnClickListener {

    /*** Instance variable */
    private lateinit var binding: ActivitySignInBinding
    private lateinit var viewModel: SignInSignUpViewModel

    /*** Overridden method */
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySignInBinding.inflate(layoutInflater)
        setContentView(binding.root)
        InitStyle.initToolBar(this, binding.toolbarTitle, binding.toolbar, getString(R.string.login))
        InitStyle.goToBack(binding.toolbar, this, SignInActivity::class.java)
        binding.tfEmail.emailVerification(binding.tfEmail, binding.tlEmail, binding.txtErrorEmail)
        binding.tfPassword.passwordVerification(binding.tfPassword, binding.tlPassowrd, binding.txtErrorPassword)
        binding.constraintLayout.hideKeyboard()
        observeValueFromVM()
        modifySubString()
        binding.onClick = this
    }

    override fun onClick(p0: View?) {
        when (p0?.id) {
            binding.btnLogin.id -> {
                if(binding.tfEmail.text?.isEmpty() == true || binding.tfEmail.text?.isEmpty() == true) {
                    isEmptyFields(binding.tfEmail, binding.tlEmail, binding.txtErrorEmail, getString(R.string.fields_should_not_empty))
                    isEmptyFields(binding.tfPassword, binding.tlPassowrd, binding.txtErrorPassword, getString(R.string.fields_should_not_empty))
                } else {
                    binding.progressBar.visibility = View.VISIBLE
                    val credentials = JSONObject()
                    credentials.put("email", binding.tfEmail.text.toString())
                    credentials.put("password", binding.tfPassword.text.toString())
                    viewModel.authenticateUserByHttp(credentials)
                }
            }
            binding.txtPassword.id -> startActivity(Intent(this, ForgotPasswordActivity::class.java))
            binding.signInWithFacebook.id -> startActivity(Intent(this, CreatePasswordActivity::class.java))
            binding.signInWithGoogle.id -> startActivity(Intent(this, BottomNavigationActivity::class.java))
        }
    }

    /*** Functions */
    private fun observeValueFromVM() {
        viewModel = ViewModelProvider(this)[SignInSignUpViewModel::class.java]
        viewModel.succeedAuthenticateUser.observe(this) {
            Toast.makeText(this, it?.token , Toast.LENGTH_SHORT).show()
            startActivity(Intent(this, SignUpActivity::class.java))
        }
        viewModel.onFailure.observe(this) {
            Toast.makeText(this, it, Toast.LENGTH_SHORT).show()
        }
        viewModel.isLoading.observe(this) {
            binding.progressBar.isVisible = it
        }
    }

    private fun modifySubString() {
        val spannableString = SpannableString(binding.txtNoAccount.text)
        spannableString.modifyString(this, getString(R.string.don_t_have_an_account_sign_up), getString(R.string.sign_up), R.color.primaryColor, true, this, SignUpActivity::class.java)
        binding.txtNoAccount.text = spannableString
        binding.txtNoAccount.movementMethod = LinkMovementMethod.getInstance()
    }

    private fun isEmptyFields(editText: EditText, txtLayout: TextInputLayout, textError: TextView, errorMessage: String) {
        if(editText.text.isEmpty()) {
            txtLayout.boxStrokeColor = Color.RED
            textError.visibility = View.VISIBLE
            textError.text = errorMessage
        }
    }

}
