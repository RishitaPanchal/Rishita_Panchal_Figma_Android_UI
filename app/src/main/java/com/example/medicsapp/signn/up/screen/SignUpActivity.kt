package com.example.medicsapp.signn.up.screen

import android.os.Bundle
import android.text.SpannableString
import android.text.method.LinkMovementMethod
import androidx.core.content.ContextCompat
import com.example.medicsapp.R
import com.example.medicsapp.databinding.ActivitySignUpBinding
import com.example.medicsapp.sign.`in`.screen.*

class SignUpActivity : BaseActivity() {

    /** Instance variable */
    private lateinit var binding: ActivitySignUpBinding

    /** Overridden method */
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySignUpBinding.inflate(layoutInflater)
        setContentView(binding.root)
        InitStyle.initToolBar(
            this,
            binding.toolbarTitle,
            binding.toolbar,
            getString(R.string.sign_up)
        )
        binding.tfEmail.emailVerification(binding.tfEmail, binding.tlEmail, binding.txtErrorEmail)
        InitStyle.goToBack(binding.toolbar, this, SignInActivity::class.java)
        modifyPrivacyPolicyString()
        checkBoxStates()
        modifySubString()
    }

    /** Functions */
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