package com.example.medicsapp.base.setup

import android.app.NotificationChannel
import android.app.NotificationManager
import android.app.PendingIntent
import android.content.Context
import android.content.Intent
import android.graphics.Bitmap
import android.os.Build
import androidx.annotation.RequiresApi
import androidx.core.app.NotificationCompat
import com.example.medicsapp.R

class BaseSetupForNotification(
    val context: Context,
    private val CHANNEL_ID: String,
    private val CHANNEL_DESC: String
) {

    /** Instance variable */
    private lateinit var notificationChannel: NotificationChannel
    private lateinit var notificationManager: NotificationManager
    private lateinit var builder: NotificationCompat.Builder
    private var inboxStyle = NotificationCompat.InboxStyle()

    /** Functions */
    @RequiresApi(Build.VERSION_CODES.O)
    fun initNotificationChannel(
        smallIcon: Int,
        title: String,
        content: String,
        className: Class<*>
    ): NotificationCompat.Builder {
        initPendingIntent(className)
        notificationChannel =
            NotificationChannel(CHANNEL_ID, CHANNEL_DESC, NotificationManager.IMPORTANCE_LOW)
        notificationManager =
            context.getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager
        builder = NotificationCompat.Builder(context, CHANNEL_ID)
            .setContentTitle(title)
            .setContentText(content)
            .setSmallIcon(smallIcon)
        return builder
    }

    private fun <T> initPendingIntent(className: Class<T>): PendingIntent {
        val intent = Intent(context, className)
        return PendingIntent.getActivity(context, 0, intent, 0)
    }

    fun setAsGroupNotification() {
        inboxStyle.addLine(context.getString(R.string.group_notification_message))
        inboxStyle.setBigContentTitle(context.getString(R.string.message_title))
        editor {
            it.setStyle(inboxStyle)
            it.setGroup(CHANNEL_ID)
            it.setGroupSummary(true)
            it.build()
        }
    }

    private fun editor(operation: (NotificationCompat.Builder) -> Unit) {
        operation(builder)
    }

    fun applyLargeIcon(icon: Bitmap) {
        editor {
            it.setLargeIcon(icon)
        }
    }

    fun notifyNotification(builder: NotificationCompat.Builder) {
        notificationManager.notify(CHANNEL_ID.toInt(), builder.build())
    }

    fun addPendingIntent(className: Class<*>) {
        editor {
            it.setContentIntent(initPendingIntent(className))
        }
    }

    fun setProgressBar(completionMessage: String) {
        editor {
           it.setProgress(0,0, true)
           notifyNotification(it)
           Thread(Runnable {
               var count = 0
               while (count<=100) {
                   count += 10
                   Thread.sleep(1000)
                   it.setProgress(100, count,false)
                   notifyNotification(it)
               }
               it.setProgress(0,0,false)
               it.setContentText(completionMessage)
               notifyNotification(it)
           }).start()
        }
    }

    fun <T> addAction(icon: Int, actionName: String, className: Class<T>) {
        editor {
            it.addAction(icon, actionName, initPendingIntent(className))
        }
    }

}