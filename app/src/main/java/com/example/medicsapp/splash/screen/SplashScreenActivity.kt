package com.example.medicsapp.splash.screen

import android.content.Intent
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import androidx.appcompat.app.AppCompatActivity
import com.example.medicsapp.OnBoardingScreen.OnBoardingScreenActivity
import com.example.medicsapp.R
import com.example.medicsapp.base.setup.BaseSetupForSharedPreferences
import com.example.medicsapp.webservices.httpurlconnection.APISelectorActivity
import com.microsoft.appcenter.AppCenter
import com.microsoft.appcenter.analytics.Analytics
import com.microsoft.appcenter.crashes.Crashes


class SplashScreenActivity : AppCompatActivity() {

    /*** Overridden method */
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        AppCenter.start(
            application, "9b351c5c-f306-4835-a8bb-1bfeb522e6cf",
            Analytics::class.java, Crashes::class.java
        )
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