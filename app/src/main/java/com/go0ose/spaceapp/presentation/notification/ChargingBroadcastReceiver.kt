package com.go0ose.spaceapp.presentation.notification

import android.app.NotificationChannel
import android.app.NotificationManager
import android.app.PendingIntent
import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.os.Build
import androidx.core.app.NotificationCompat
import com.go0ose.spaceapp.R
import com.go0ose.spaceapp.presentation.MainActivity

class ChargingBroadcastReceiver : BroadcastReceiver() {
    override fun onReceive(context: Context, intent: Intent) {
        if (intent.action == Intent.ACTION_POWER_CONNECTED) {

            val notificationManager =
                context.getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager
            val channelId = "charging_channel"
            val channelName = "Charging"
            val importance = NotificationManager.IMPORTANCE_HIGH
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
                val channel = NotificationChannel(channelId, channelName, importance)
                notificationManager.createNotificationChannel(channel)
            }

            val intent = Intent(context, MainActivity::class.java)
            intent.putExtra("fragment", "map")
            intent.flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
            val pendingIntent = PendingIntent.getActivity(context, 0, intent, 0)
            val exploreAction = NotificationCompat.Action.Builder(
                R.drawable.ic_push,
                "Explore",
                pendingIntent
            ).build()

            val builder = NotificationCompat.Builder(context, channelId)
                .setSmallIcon(R.drawable.ic_push)
                .setContentTitle("It's time to explore our Earth!")
                .addAction(exploreAction)
                .setPriority(NotificationCompat.PRIORITY_HIGH)
                .setAutoCancel(true)
            notificationManager.notify(0, builder.build())
        }
    }
}

