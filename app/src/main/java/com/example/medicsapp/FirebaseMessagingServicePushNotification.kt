package com.example.medicsapp

import android.os.Build
import androidx.annotation.RequiresApi
import com.example.medicsapp.base.setup.BaseSetupForNotification
import com.example.medicsapp.splash.screen.SplashScreenActivity
import com.google.firebase.messaging.FirebaseMessagingService
import com.google.firebase.messaging.RemoteMessage

class FirebaseMessagingServicePushNotification : FirebaseMessagingService() {

    /** Instance variable */
    private var CHANNEL_ID = "12345"
    private var CHANNEL_DESC = "Test Notification"
    private var baseNotification: BaseSetupForNotification =
        BaseSetupForNotification(this, CHANNEL_ID, CHANNEL_DESC)

    /** Overridden method */
    @RequiresApi(Build.VERSION_CODES.O)
    override fun onMessageReceived(message: RemoteMessage) {
        super.onMessageReceived(message)
        if (message.notification != null) {
            message.notification?.title?.let { title ->
                message.notification?.body?.let { message ->
                    generateNotification(
                        title,
                        message
                    )
                }
            }
        }
    }

    /** Functions */
    @RequiresApi(Build.VERSION_CODES.O)
    fun generateNotification(title: String, message: String) {
        buildNotification {
            val builder = it.initNotificationChannel(
                R.drawable.email_icon,
                title,
                message,
                SplashScreenActivity::class.java
            )
            it.notifyNotification(builder)
        }
    }

    private fun buildNotification(obj: (BaseSetupForNotification) -> Unit) {
        obj(baseNotification)
    }

}
