package com.example.medicsapp.sign.`in`.screen

import android.graphics.Color
import android.os.Bundle
import android.text.*
import android.text.method.LinkMovementMethod
import android.view.View
import com.example.medicsapp.R
import com.example.medicsapp.databinding.ActivitySignInBinding

class SignInActivity : BaseActivity(), View.OnClickListener {

    /*** Instance variable */
    private lateinit var binding: ActivitySignInBinding

    /*** Overridden method */
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySignInBinding.inflate(layoutInflater)
        setContentView(binding.root)
        InitStyle.initToolBar(this, binding.toolbarTitle, binding.toolbar, "Login")
        binding.tfEmail.emailVerification(binding.tfEmail, binding.tlEmail, binding.txtErrorEmail)
        binding.tfPassword.passwordVerification(binding.tfPassword, binding.tlPassowrd, binding.txtErrorPassword)
        dismissKeyBoard()
        binding.onClick = this
        val spannableString = SpannableString(binding.txtNoAccount.text)
        spannableString.applyColor(this, getString(R.string.don_t_have_an_account_sign_up), getString(R.string.sign_up), R.color.primaryColor, true, this, SignInActivity::class.java)
        binding.txtNoAccount.text = spannableString
        binding.txtNoAccount.movementMethod = LinkMovementMethod.getInstance()
    }

    override fun onClick(p0: View?) {
        when (p0?.id) {
            binding.btnLogin.id -> {
                if (binding.tfEmail.text?.isEmpty() == true) {
                    binding.tlEmail.boxStrokeColor = Color.RED
                    binding.txtErrorEmail.visibility = View.VISIBLE
                    binding.txtErrorEmail.text = getString(R.string.fields_should_not_empty)
                }
                if (binding.tfPassword.text?.isEmpty() == true) {
                    binding.tlPassowrd.boxStrokeColor = Color.RED
                    binding.txtErrorPassword.visibility = View.VISIBLE
                    binding.txtErrorPassword.text = getString(R.string.fields_should_not_empty)
                }
            }
        }
    }

    /*** Functions */
    private fun dismissKeyBoard() {
        binding.constraintLayout.setOnClickListener {
            binding.constraintLayout.hideKeyboard()
        }
    }

}
