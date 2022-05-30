package com.example.medicsapp.notifications

import android.graphics.BitmapFactory
import android.os.Build
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.annotation.RequiresApi
import com.example.medicsapp.R
import com.example.medicsapp.base.setup.BaseAppActivity
import com.example.medicsapp.base.setup.BaseSetupForNotification
import com.example.medicsapp.databinding.ActivityNotificationBinding
import com.example.medicsapp.splash.screen.SplashScreenActivity
import com.example.medicsapp.webservices.httpurlconnection.SignInSignUpViewModel

class NotificationActivity : BaseAppActivity<ActivityNotificationBinding, SignInSignUpViewModel>(
    ActivityNotificationBinding::inflate,
    SignInSignUpViewModel::class.java
), View.OnClickListener {

    /** Instance variable */
    private var CHANNEL_ID = "12345"
    private var CHANNEL_DESC = "Test notification"
    private lateinit var baseNotification: BaseSetupForNotification

    /** Overridden method */
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        baseNotification = BaseSetupForNotification(this, CHANNEL_ID, CHANNEL_DESC)
        binding.onClick = this
    }

    @RequiresApi(Build.VERSION_CODES.O)
    override fun onClick(p0: View?) {
        when (p0?.id) {
            binding.btnSimpleNotification.id -> {
                buildNotification {
                    val builder = it.initNotificationChannel(
                        R.drawable.email_icon,
                        getString(R.string.message_title),
                        getString(R.string.message_desc),
                        SplashScreenActivity::class.java
                    )
                    it.applyLargeIcon(
                        BitmapFactory.decodeResource(
                            resources,
                            R.drawable.email_icon
                        )
                    )
                    it.addAction(
                        R.drawable.email_icon,
                        getString(R.string.reply_action),
                        SplashScreenActivity::class.java
                    )
                    it.addPendingIntent(SplashScreenActivity::class.java)
                    it.notifyNotification(builder)
                }
            }
            binding.btnProgressNotification.id -> {
                buildNotification {
                    val builder = it.initNotificationChannel(
                        R.drawable.email_icon,
                        getString(R.string.message_title),
                        getString(R.string.message_desc),
                        SplashScreenActivity::class.java
                    )
                    it.setAsGroupNotification()
                    it.notifyNotification(builder)
                }
            }
            binding.btnPushNotification.id -> {
                buildNotification {
                    val builder = it.initNotificationChannel(
                        R.drawable.email_icon,
                        getString(R.string.message_title),
                        getString(R.string.message_desc),
                        SplashScreenActivity::class.java
                        )
                    it.setProgressBar(getString(R.string.download_Completed))
                    it.notifyNotification(builder)
                }
            }
        }
    }

    /** Function */
    private fun buildNotification(obj: (BaseSetupForNotification) -> Unit) {
        obj(baseNotification)
    }

}