package com.example.medicsapp.forgot.password.screen

import android.graphics.Typeface
import android.os.Bundle
import android.text.Spannable
import android.text.SpannableString
import android.text.SpannableStringBuilder
import android.text.method.LinkMovementMethod
import android.text.style.ForegroundColorSpan
import android.text.style.StyleSpan
import android.widget.EditText
import android.widget.TextView
import androidx.core.content.ContextCompat
import androidx.core.widget.doOnTextChanged
import com.example.medicsapp.R
import com.example.medicsapp.databinding.ActivityOtpVerificationScreenBinding
import com.example.medicsapp.sign.`in`.screen.BaseActivity
import com.example.medicsapp.sign.`in`.screen.InitStyle
import com.example.medicsapp.sign.`in`.screen.hideKeyboard
import com.example.medicsapp.sign.`in`.screen.modifyString
import com.example.medicsapp.signn.up.screen.SignUpActivity
import com.google.android.material.textfield.TextInputLayout

class OtpVerificationScreen : BaseActivity() {

    /** Instance variables */
    private lateinit var binding: ActivityOtpVerificationScreenBinding

    /** Overridden Methods */
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityOtpVerificationScreenBinding.inflate(layoutInflater)
        setContentView(binding.root)
        modifySubString()
        InitStyle.initToolBar(this, null, binding.toolbar, null)
        InitStyle.goToBack(binding.toolbar, this, ForgotPasswordActivity::class.java)
        binding.constraintLayout.hideKeyboard()
        setSpanString(getString(R.string.verification_subtitle_start_string), binding.txtVerificationSubtitle)
        manageOTPInputs()
    }

    /** Functions */
    private fun manageOTPInputs() {
        checkInput(binding.tfInputOne, binding.tlInputOne, binding.tfInputTwo)
        checkInput(binding.tfInputTwo, binding.tlInputTwo, binding.tfInputThree)
        checkInput(binding.tfInputThree, binding.tlInputThree, binding.tfInputFour)
    }

    private fun checkInput(editTextFirst: EditText, txtLayout: TextInputLayout, editTextNext: EditText) {
        editTextFirst.doOnTextChanged { text, _, _, _ ->
            if(text?.isNotEmpty() == true) {
                editTextNext.requestFocus()
            }
        }
    }

    private fun modifySubString() {
        val spannableString = SpannableString(binding.txtResend.text)
        spannableString.modifyString(this, getString(R.string.didn_t_receive_the_code_resend), getString(R.string.resend), R.color.primaryColor,true, this,  SignUpActivity::class.java)
        binding.txtResend.text = spannableString
        binding.txtResend.movementMethod = LinkMovementMethod.getInstance()
    }

    private fun setSpanString(startString: String, textView: TextView) {
        val builder = SpannableStringBuilder()
        val txtSpannable = SpannableString(binding.txtVerificationSubtitle.text)
        val boldSpan = StyleSpan(Typeface.BOLD)
        val color = ContextCompat.getColor(this, R.color.grayTextColor)
        txtSpannable.setSpan(boldSpan, startString.length,  binding.txtVerificationSubtitle.text.length, Spannable.SPAN_EXCLUSIVE_EXCLUSIVE)
        txtSpannable.setSpan(ForegroundColorSpan(color), startString.length,  binding.txtVerificationSubtitle.text.length, Spannable.SPAN_EXCLUSIVE_EXCLUSIVE)
        builder.append(txtSpannable)
        textView.setText(builder, TextView.BufferType.SPANNABLE)
    }

}