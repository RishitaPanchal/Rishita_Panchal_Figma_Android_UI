package com.example.medicsapp.forgot.password.screen

import android.content.Intent
import android.os.Bundle
import android.text.InputType
import androidx.core.content.ContextCompat
import com.example.medicsapp.R
import com.example.medicsapp.databinding.ActivityForgotPasswordBinding
import com.example.medicsapp.sign.`in`.screen.BaseActivity
import com.example.medicsapp.sign.`in`.screen.InitStyle
import com.example.medicsapp.sign.`in`.screen.SignInActivity

class ForgotPasswordActivity : BaseActivity() {

    /** Instance variables */
    private lateinit var binding: ActivityForgotPasswordBinding

    /** Overridden Methods */
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityForgotPasswordBinding.inflate(layoutInflater)
        setContentView(binding.root)
        InitStyle.initToolBar(this, null, binding.toolbar, null)
        InitStyle.goToBack(binding.toolbar, this, SignInActivity::class.java)
        manageSegmentControl()
        binding.btnResetPassword.setOnClickListener {
            startActivity(Intent(this@ForgotPasswordActivity, OtpVerificationScreen::class.java))
        }
    }

    /** Functions */
    private fun manageSegmentControl() {
        binding.radioGroup.setOnCheckedChangeListener { _, _ ->
            when(binding.radioGroup.checkedRadioButtonId) {
                binding.segmentButtonEmail.id -> segmentEmailSelected()
                binding.segmentButtonPhone.id -> segmentPhoneSelected()
            }
        }
    }

    private fun segmentEmailSelected() {
        binding.segmentButtonEmail.background = (ContextCompat.getDrawable(this, R.drawable.selected_segment_button))
        binding.segmentButtonEmail.setTextColor(ContextCompat.getColor(this, R.color.primaryColor))
        binding.segmentButtonPhone.background = null
        binding.segmentButtonPhone.setTextColor(ContextCompat.getColor(this, R.color.grayForgotPasswordTitle))
        binding.tfEmailPhone.setCompoundDrawablesWithIntrinsicBounds(R.drawable.email_icon, 0, 0, 0)
        binding.tfEmailPhone.hint = getString(R.string.email)
        binding.tfEmailPhone.inputType = InputType.TYPE_TEXT_VARIATION_EMAIL_ADDRESS
    }

    private fun segmentPhoneSelected() {
        binding.segmentButtonPhone.background = (ContextCompat.getDrawable(this, R.drawable.selected_segment_button))
        binding.segmentButtonPhone.setTextColor(ContextCompat.getColor(this, R.color.primaryColor))
        binding.segmentButtonEmail.background = null
        binding.segmentButtonEmail.setTextColor(ContextCompat.getColor(this, R.color.grayForgotPasswordTitle))
        binding.tfEmailPhone.setCompoundDrawablesWithIntrinsicBounds(R.drawable.phone_icon, 0, 0, 0)
        binding.tfEmailPhone.hint = getString(R.string.phone)
        binding.tfEmailPhone.inputType = InputType.TYPE_CLASS_PHONE
    }

}