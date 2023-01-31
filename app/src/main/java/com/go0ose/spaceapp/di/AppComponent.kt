package com.go0ose.spaceapp.di

import android.content.Context
import com.go0ose.spaceapp.di.module.NavigationModule
import com.go0ose.spaceapp.di.module.NetworkModule
import com.go0ose.spaceapp.di.module.PresenterModule
import com.go0ose.spaceapp.di.module.RetrofitModule
import com.go0ose.spaceapp.presentation.MainActivity
import com.go0ose.spaceapp.presentation.screens.details.DetailsFragment
import com.go0ose.spaceapp.presentation.screens.main.MainFragment
import com.go0ose.spaceapp.presentation.screens.map.MapFragment
import dagger.BindsInstance
import dagger.Component
import javax.inject.Singleton

@Singleton
@Component(
    modules = [
        NetworkModule::class,
        RetrofitModule::class,
        NavigationModule::class,
        PresenterModule::class
    ]
)
interface AppComponent {

    fun inject(target: MainActivity)
    fun inject(target: MainFragment)
    fun inject(target: MapFragment)
    fun inject(target: DetailsFragment)

    @Component.Builder
    interface Builder {

        @BindsInstance
        fun buildContext(context: Context): Builder

        fun build(): AppComponent
    }
}