package com.example.medicsapp.signn.up.screen

import android.content.Intent
import android.os.Bundle
import android.text.SpannableString
import android.text.method.LinkMovementMethod
import android.view.View
import android.widget.Toast
import androidx.core.content.ContextCompat
import androidx.core.view.isVisible
import androidx.lifecycle.ViewModelProvider
import com.example.medicsapp.R
import com.example.medicsapp.databinding.ActivitySignUpBinding
import com.example.medicsapp.sign.`in`.screen.*
import com.example.medicsapp.webservices.httpurlconnection.APICallingType
import com.example.medicsapp.webservices.httpurlconnection.SignInSignUpViewModel

class SignUpActivity : BaseActivity(), View.OnClickListener {

    /** Instance Variables */
    private lateinit var binding: ActivitySignUpBinding
    private lateinit var viewModel: SignInSignUpViewModel

    /** Overridden method */
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySignUpBinding.inflate(layoutInflater)
        setContentView(binding.root)
        observeValueFromVM()
        binding.tfUser.setText(getString(R.string.signup_name))
        binding.tfEmail.setText(getString(R.string.sign_up_email))
        binding.tfPassword.setText(getString(R.string.sign_up_password))
        InitStyle.initToolBar(
            this,
            binding.toolbarTitle,
            binding.toolbar,
            getString(R.string.sign_up)
        )
        InitStyle.goToBack(binding.toolbar, this, SignInActivity::class.java)
        binding.constraintLayout.hideKeyboard()
        binding.tfEmail.emailVerification(binding.tfEmail, binding.tlEmail, binding.txtErrorEmail)
        modifyPrivacyPolicyString()
        checkBoxStates()
        modifySubString()
        binding.onClick = this
    }

    override fun onClick(p0: View?) {
        when (p0?.id) {
            binding.btnLogin.id -> {
                if (binding.tfUser.text.toString() != "" && binding.tfEmail.text.toString() != "" && binding.tfPassword.text.toString() != "") {
                    viewModel.selectCreateUserAPICallingType(APICallingType, binding.tfUser.text.toString(), binding.tfEmail.text.toString(), binding.tfPassword.text.toString())
                } else {
                    Toast.makeText(this, "Not Empty fields", Toast.LENGTH_LONG).show()
                }
            }
        }
    }

    /** Functions */
    private fun observeValueFromVM() {
        viewModel = ViewModelProvider(this)[SignInSignUpViewModel::class.java]
        viewModel.succeedCreateUser.observe(this) {
            Toast.makeText(this, it?.token , Toast.LENGTH_SHORT).show()
            startActivity(Intent(this, SignInActivity::class.java))
        }
        viewModel.onFailure.observe(this) {
            Toast.makeText(this, it, Toast.LENGTH_SHORT).show()
        }
        viewModel.isLoading.observe(this) {
            binding.progressBar.isVisible = it
        }
    }

    private fun modifyPrivacyPolicyString() {
        val spannableString = SpannableString(binding.txtPrivacyPolicy.text)
        spannableString.modifyString(
            this,
            getString(R.string.txt_privacy_policy),
            getString(R.string.privacy_and_policy),
            R.color.primaryColor,
            false
        )
        spannableString.modifyString(
            this,
            getString(R.string.txt_privacy_policy),
            getString(R.string.terms_and_condition),
            R.color.primaryColor,
            false
        )
        binding.txtPrivacyPolicy.text = spannableString
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
            SignInActivity::class.java
        )
        binding.txtNoAccount.text = spannableString
        binding.txtNoAccount.movementMethod = LinkMovementMethod.getInstance()
    }

    private fun checkBoxStates() {
        binding.checkBox.setOnClickListener {
            if (binding.checkBox.drawable.constantState == ContextCompat.getDrawable(
                    this,
                    R.drawable.checkbox_inactive
                )?.constantState
            ) {
                binding.checkBox.setImageResource(R.drawable.checkbox_active)
            } else {
                binding.checkBox.setImageResource(R.drawable.checkbox_inactive)
            }
        }
    }

}