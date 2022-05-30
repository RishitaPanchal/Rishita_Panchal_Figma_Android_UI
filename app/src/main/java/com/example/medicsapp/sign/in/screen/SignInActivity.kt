package com.example.medicsapp.sign.`in`.screen

import android.content.Intent
import android.graphics.Color
import android.os.Bundle
import android.text.*
import android.text.method.LinkMovementMethod
import android.util.Log
import android.view.View
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.core.view.isVisible
import com.example.medicsapp.R
import com.example.medicsapp.base.setup.BaseAppActivity
import com.example.medicsapp.base.setup.BaseSetupForSharedPreferences
import com.example.medicsapp.databinding.ActivitySignInBinding
import com.example.medicsapp.forgot.password.screen.CreatePasswordActivity
import com.example.medicsapp.forgot.password.screen.ForgotPasswordActivity
import com.example.medicsapp.home.screen.ui.BottomNavigationActivity
import com.example.medicsapp.signn.up.screen.SignUpActivity
import com.example.medicsapp.webservices.httpurlconnection.APICallingType
import com.example.medicsapp.webservices.httpurlconnection.SignInSignUpViewModel
import com.google.android.material.textfield.TextInputLayout

class SignInActivity : BaseAppActivity<ActivitySignInBinding, SignInSignUpViewModel>(
    ActivitySignInBinding::inflate,
    SignInSignUpViewModel::class.java
), View.OnClickListener {

    /*** Overridden method */
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        InitStyle.initToolBar(
            this,
            binding.toolbarTitle,
            binding.toolbar,
            getString(R.string.login)
        )
        if(BaseSetupForSharedPreferences.isLogin(this)) {
            Toast.makeText(this, "You are already logged in!!", Toast.LENGTH_LONG).show()
            val i = Intent(applicationContext, BottomNavigationActivity::class.java)
            i.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK)
            i.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
            startActivity(i)
        }
        InitStyle.goToBack(binding.toolbar, this, SignInActivity::class.java)
        binding.tfEmail.emailVerification(binding.tfEmail, binding.tlEmail, binding.txtErrorEmail)
        binding.tfPassword.passwordVerification(
            binding.tfPassword,
            binding.tlPassowrd,
            binding.txtErrorPassword
        )
        binding.constraintLayout.hideKeyboard()
        observeValueFromVM()
        modifySubString()
        binding.onClick = this
    }

    override fun onClick(p0: View?) {
        when (p0?.id) {
            binding.btnLogin.id -> {
                if (binding.tfEmail.text?.isEmpty() == true || binding.tfEmail.text?.isEmpty() == true) {
                    isEmptyFields(
                        binding.tfEmail,
                        binding.tlEmail,
                        binding.txtErrorEmail,
                        getString(R.string.fields_should_not_empty)
                    )
                    isEmptyFields(
                        binding.tfPassword,
                        binding.tlPassowrd,
                        binding.txtErrorPassword,
                        getString(R.string.fields_should_not_empty)
                    )
                } else {
                    viewModel.selectAuthenticateAPICallingType(
                        APICallingType,
                        binding.tfEmail.text.toString(),
                        binding.tfPassword.text.toString()
                    )
                }
            }
            binding.txtPassword.id -> startActivity(
                Intent(
                    this,
                    ForgotPasswordActivity::class.java
                )
            )
            binding.signInWithFacebook.id -> startActivity(
                Intent(
                    this,
                    CreatePasswordActivity::class.java
                )
            )
            binding.signInWithGoogle.id -> startActivity(
                Intent(
                    this,
                    BottomNavigationActivity::class.java
                )
            )
        }
    }

    private fun observeValueFromVM() {
        viewModel.succeedAuthenticateUser.observe(this) {
            Toast.makeText(this, it?.token, Toast.LENGTH_SHORT).show()
            BaseSetupForSharedPreferences.createLoginSession(this, it?.token.toString())
            startActivity(Intent(this, BottomNavigationActivity::class.java))
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
        spannableString.modifyString(
            this,
            getString(R.string.don_t_have_an_account_sign_up),
            getString(R.string.sign_up),
            R.color.primaryColor,
            true,
            this,
            SignUpActivity::class.java
        )
        binding.txtNoAccount.text = spannableString
        binding.txtNoAccount.movementMethod = LinkMovementMethod.getInstance()
    }

    private fun isEmptyFields(
        editText: EditText,
        txtLayout: TextInputLayout,
        textError: TextView,
        errorMessage: String
    ) {
        if (editText.text.isEmpty()) {
            txtLayout.boxStrokeColor = Color.RED
            textError.visibility = View.VISIBLE
            textError.text = errorMessage
        }
    }

}
