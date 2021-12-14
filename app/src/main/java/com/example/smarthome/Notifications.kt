package com.example.smarthome


import android.app.Notification
import android.app.NotificationChannel
import android.app.NotificationManager
import android.app.PendingIntent
import android.content.Context
import android.content.Intent
import android.os.Build
import androidx.core.app.NotificationCompat

class  Notifications(){

    private val NOTIFIYTAG="new request"
    var notifTag = 0
    fun Notify(context: Context,message:String,number:Int){
        createNotificationChannel(context)
        val intent=Intent(context,MainActivity::class.java)

        val builder=NotificationCompat.Builder(context, "all_notifications")
            .setDefaults(Notification.DEFAULT_ALL)
            .setContentTitle("New Alert")
            .setContentText(message)
            .setNumber(1)
            .setSmallIcon(R.drawable.ic_smart_home)
            .setContentIntent(PendingIntent.getActivity(context
                ,0,intent,PendingIntent.FLAG_UPDATE_CURRENT))
            .setAutoCancel(true)

        val nm=context.getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager

        nm.notify(number.toString(), 0, builder.build())

    }

    private fun createNotificationChannel(context: Context) {

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            val channelId = "all_notifications" // You should create a String resource for this instead of storing in a variable
            val mChannel = NotificationChannel(
                channelId,
                "General Notifications",
                NotificationManager.IMPORTANCE_DEFAULT
            )
            mChannel.description = "This is default channel used for all other notifications"

            val notificationManager =
                context.getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager
            notificationManager.createNotificationChannel(mChannel)
        }
    }



}