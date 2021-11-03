package com.example.notifications_demo

import android.app.Notification
import android.app.NotificationChannel
import android.app.NotificationManager
import android.app.PendingIntent
import android.content.Intent
import android.graphics.BitmapFactory
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.core.app.NotificationCompat
import androidx.core.app.NotificationManagerCompat
import com.google.firebase.crashlytics.buildtools.reloc.com.google.errorprone.annotations.Var

class MainActivity : AppCompatActivity() {
    private val channelId = "myapp.notifications"
    private val description = "Notification App Example"
    lateinit var builder: Notification.Builder
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val notificationManager = getSystemService(NOTIFICATION_SERVICE) as NotificationManager
        val intent = Intent(this, MainActivity2::class.java)
        val pendingIntent =
            PendingIntent.getActivities(this, 0, arrayOf(intent), PendingIntent.FLAG_UPDATE_CURRENT)

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            val notificationChannel = NotificationChannel(channelId, description, NotificationManager.IMPORTANCE_HIGH)
            notificationManager.createNotificationChannel(notificationChannel)

             builder = Notification.Builder(this, channelId)
                .setSmallIcon(R.drawable.ic_launcher_background)
                .setLargeIcon(BitmapFactory.decodeResource(this.resources, R.drawable.ic_launcher_foreground))
                .setContentIntent(pendingIntent)
                .setContentTitle("My Notification")
                .setContentText("Hello")
        } else  {
             builder = Notification.Builder(this)
                .setSmallIcon(R.drawable.ic_launcher_background)
                .setLargeIcon(BitmapFactory.decodeResource(this.resources, R.drawable.ic_launcher_foreground))
                .setContentIntent(pendingIntent)
                .setContentTitle("My Notification")
                .setContentText("Hello")
        }

           notificationManager. notify(1234, builder.build())

}
}
