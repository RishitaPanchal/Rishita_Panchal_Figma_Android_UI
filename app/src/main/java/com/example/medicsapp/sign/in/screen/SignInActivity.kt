package com.example.medicsapp.sign.`in`.screen

import android.graphics.Color
import android.os.Bundle
import android.text.*
import android.text.method.LinkMovementMethod
import android.view.View
import android.widget.EditText
import android.widget.TextView
import com.example.medicsapp.R
import com.example.medicsapp.databinding.ActivitySignInBinding
import com.example.medicsapp.signn.up.screen.SignUpActivity
import com.google.android.material.textfield.TextInputLayout

class SignInActivity : BaseActivity(), View.OnClickListener {

    /*** Instance variable */
    private lateinit var binding: ActivitySignInBinding

    /*** Overridden method */
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySignInBinding.inflate(layoutInflater)
        setContentView(binding.root)
        InitStyle.initToolBar(this, binding.toolbarTitle, binding.toolbar, getString(R.string.login))
        InitStyle.goToBack(binding.toolbar, this, SignInActivity::class.java)
        binding.tfEmail.emailVerification(binding.tfEmail, binding.tlEmail, binding.txtErrorEmail)
        binding.tfPassword.passwordVerification(binding.tfPassword, binding.tlPassowrd, binding.txtErrorPassword)
        binding.onClick = this
        binding.constraintLayout.hideKeyboard()
        modifySubString()
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
                }
            }
        }
    }

    /** Functions */
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
