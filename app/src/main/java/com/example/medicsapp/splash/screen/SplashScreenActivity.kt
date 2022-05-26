package com.example.medicsapp.splash.screen

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import com.example.medicsapp.OnBoardingScreen.OnBoardingScreenActivity
import com.example.medicsapp.R

class SplashScreenActivity : AppCompatActivity() {

    /*** Overridden method */
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash_screen)
        goToOnBoardScreen()
    }

    /*** Functions */
    private fun goToOnBoardScreen() {
        Handler(Looper.getMainLooper()).postDelayed({
            Intent(this, OnBoardingScreenActivity::class.java).apply {
                startActivity(this)
            }
            finish()
        }, 2000)
    }

}