package com.go0ose.spaceapp

import android.app.Application
import android.content.Context
import com.go0ose.spaceapp.di.AppComponent
import com.go0ose.spaceapp.di.DaggerAppComponent

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
}