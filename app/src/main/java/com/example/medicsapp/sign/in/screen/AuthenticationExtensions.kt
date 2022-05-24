package com.example.medicsapp.sign.`in`.screen

import android.app.Activity
import android.content.Context
import android.content.Intent
import android.graphics.Color
import android.text.Spannable
import android.text.SpannableString
import android.text.TextPaint
import android.text.style.ClickableSpan
import android.text.style.ForegroundColorSpan
import android.util.Patterns
import android.view.View
import android.view.inputmethod.InputMethodManager
import android.widget.EditText
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import androidx.core.widget.doOnTextChanged
import com.example.medicsapp.R
import com.google.android.material.textfield.TextInputEditText
import com.google.android.material.textfield.TextInputLayout

/*** Extensions */
// Extension for email verification
fun TextInputEditText.emailVerification(
    editText: EditText,
    txtLayout: TextInputLayout,
    textView: TextView
) {
    editText.doOnTextChanged { _, _, _, _ ->
        if (Patterns.EMAIL_ADDRESS.matcher(editText.text.toString()).matches()) {
            txtLayout.boxStrokeColor = ContextCompat.getColor(context, R.color.primaryColor)
            editText.setCompoundDrawablesWithIntrinsicBounds(
                R.drawable.email_icon,
                0,
                R.drawable.email_varified,
                0
            )
            textView.text = ""
            textView.visibility = View.GONE
        } else {
            editText.setCompoundDrawablesWithIntrinsicBounds(R.drawable.email_icon, 0, 0,0)
            txtLayout.boxStrokeColor = Color.RED
            textView.visibility = View.VISIBLE
            textView.text = context.getString(R.string.invalid_email)
        }
    }
}

// Extension for password verification
fun TextInputEditText.passwordVerification(
    editText: EditText,
    txtLayout: TextInputLayout,
    textView: TextView
) {
    editText.doOnTextChanged { _, _, _, _ ->
        if (editText.text.length >= 5) {
            textView.text = ""
            txtLayout.boxStrokeColor = ContextCompat.getColor(context, R.color.primaryColor)
        } else {
            textView.visibility = View.VISIBLE
            textView.text = context.getString(R.string.invalid_password)
            txtLayout.boxStrokeColor = Color.RED
        }
    }
}

// Extension for dismiss keyboard
fun View.hideKeyboard() {
    val inputMethodManager = context.getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
    inputMethodManager.hideSoftInputFromWindow(windowToken, 0)
}

// Extension for Spannable String
fun SpannableString.applyColor(
    context: Context,
    entireString: String,
    subString: String,
    color: Int,
    clickableSpan: Boolean = false,
    startActivity: Activity,
    destinationActivity: Class<*>
): SpannableString {
    val start = entireString.indexOf(subString)
    val end = start + subString.length
    if (clickableSpan) {
        val clickSpan = object : ClickableSpan() {
            override fun onClick(p0: View) {
                startActivity.startActivity(Intent(startActivity, destinationActivity))
            }
            override fun updateDrawState(ds: TextPaint) {
                super.updateDrawState(ds)
                ds.isUnderlineText = false
            }
        }
        this.setSpan(clickSpan, start, end, Spannable.SPAN_EXCLUSIVE_EXCLUSIVE)
    }
    this.setSpan(
        ForegroundColorSpan(ContextCompat.getColor(context, color)),
        start,
        end,
        0
    )
    return this
}

// Static method to init toolbar style
class InitStyle {

    companion object {
        fun initToolBar(
            activity: AppCompatActivity,
            toolbarTitle: TextView,
            toolBar: androidx.appcompat.widget.Toolbar,
            title: String
        ) {
            activity.setSupportActionBar(toolBar)
            toolBar.setNavigationIcon(R.drawable.back_icon)
            toolbarTitle.text = title
            activity.supportActionBar?.setDisplayShowTitleEnabled(false)
        }
    }

}