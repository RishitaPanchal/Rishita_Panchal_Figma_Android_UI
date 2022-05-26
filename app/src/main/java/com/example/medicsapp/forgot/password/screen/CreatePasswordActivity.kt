package com.example.medicsapp.forgot.password.screen

import android.os.Bundle
import com.example.medicsapp.databinding.ActivityCreatePasswordBinding
import com.example.medicsapp.sign.`in`.screen.*

class CreatePasswordActivity : BaseActivity() {

    /** Instance variable */
    private lateinit var binding: ActivityCreatePasswordBinding

    /** Overridden Method */
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityCreatePasswordBinding.inflate(layoutInflater)
        setContentView(binding.root)
        binding.constraintLayout.hideKeyboard()
        InitStyle.initToolBar(this, null, binding.toolbar, null)
        InitStyle.goToBack(binding.toolbar, this, SignInActivity::class.java)
        binding.tfPassword.passwordVerification(binding.tfPassword, binding.tlPassowrd, binding.txtErrorPassword)
        binding.tfConfirmPassword.passwordVerification(binding.tfConfirmPassword, binding.tlConfirmPassowrd, binding.txtErrorPassword)
    }

}