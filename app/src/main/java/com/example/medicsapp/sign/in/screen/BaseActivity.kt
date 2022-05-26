package com.example.medicsapp.sign.`in`.screen

import android.graphics.Color
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import com.example.medicsapp.R

open class BaseActivity: AppCompatActivity() {

    /*** Overridden method */
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        statusBarColor()
    }

    /*** Function */
    private fun statusBarColor() {
        window.statusBarColor =
            Color.parseColor("#" + Integer.toHexString(ContextCompat.getColor(this, R.color.white)))
    }

}