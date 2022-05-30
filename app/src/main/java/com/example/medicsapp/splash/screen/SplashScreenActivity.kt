package com.example.medicsapp.splash.screen

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import com.example.medicsapp.OnBoardingScreen.OnBoardingScreenActivity
import com.example.medicsapp.R
import com.example.medicsapp.base.setup.BaseSetupForSharedPreferences
import com.example.medicsapp.webservices.httpurlconnection.APISelectorActivity

class SplashScreenActivity : AppCompatActivity() {

    /*** Overridden method */
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash_screen)
        if(BaseSetupForSharedPreferences.isFirstRun(this)) {
            startActivity(Intent(applicationContext, APISelectorActivity::class.java))
        } else {
            goToOnBoardScreen()
        }
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