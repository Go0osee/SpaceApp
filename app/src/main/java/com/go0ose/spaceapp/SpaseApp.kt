package com.go0ose.spaceapp

import android.app.Application
import android.content.Context
import android.content.Intent
import android.content.IntentFilter
import android.util.Log
import com.go0ose.spaceapp.di.AppComponent
import com.go0ose.spaceapp.di.DaggerAppComponent
import com.go0ose.spaceapp.presentation.notification.ChargingBroadcastReceiver
import com.google.firebase.messaging.FirebaseMessaging

class SpaseApp: Application() {

    companion object {
        var appComponent: AppComponent? = null
        fun initDagger(
            context: Context
        ) {
            appComponent = DaggerAppComponent.builder()
                .buildContext(context)
                .build()
        }
    }

    override fun onCreate() {
        super.onCreate()

        val receiver = ChargingBroadcastReceiver()
        val filter = IntentFilter()
        filter.addAction(Intent.ACTION_POWER_CONNECTED)
        registerReceiver(receiver, filter)

        FirebaseMessaging.getInstance().token.addOnCompleteListener { task ->
            if(!task.isSuccessful){
                return@addOnCompleteListener
            }
            val token = task.result
            Log.e("TAG", "Token -> $token")
        }
    }
}