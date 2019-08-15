package com.example.notifications

import android.app.Notification
import android.app.NotificationChannel
import android.app.NotificationManager
import android.app.PendingIntent
import android.content.Context
import android.content.Intent
import android.content.res.Resources
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.core.app.NotificationCompat
import androidx.core.content.ContextCompat
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    companion object{
        const val NOTIFICATION_ID_INSTENT = 12
        const val INTENT_KEY = "key"
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)


        btn_get_notification.setOnClickListener {
            val intent = Intent(this, FullscreenActivity::class.java)
            intent.putExtra(INTENT_KEY, "Notification Tapped")
            val pendingIntent = PendingIntent.getActivity(this, 0, intent, PendingIntent.FLAG_ONE_SHOT)

            intent.putExtra(INTENT_KEY, "NICE")
            val pendingIntent2 = PendingIntent.getActivity(this, 1, intent, PendingIntent.FLAG_ONE_SHOT)



            val notificationManager = getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager
            val channelId = "channelId"

            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O){
                val name = "Annoying notification"
                val descriptionText = "This notification is just here to annoy you"
                val importance = NotificationManager.IMPORTANCE_HIGH

                val channel = NotificationChannel(channelId, name, importance)
                channel.description = descriptionText

                notificationManager.createNotificationChannel(channel)
            }

            val builder = NotificationCompat.Builder(this, channelId)
                .setSmallIcon(R.drawable.notification_icon_background)
                .setContentTitle("Title")
                .setContentText("Text")
                .setColor(ContextCompat.getColor(this, R.color.colorPrimary))
                .setPriority(NotificationManager.IMPORTANCE_HIGH)
                .setDefaults(Notification.DEFAULT_ALL)
                .setAutoCancel(true)
                .setContentIntent(pendingIntent)
                .addAction(R.drawable.ic_action_name, "Action", pendingIntent2)

            notificationManager.notify(NOTIFICATION_ID_INSTENT, builder.build())


        }

    }
}
