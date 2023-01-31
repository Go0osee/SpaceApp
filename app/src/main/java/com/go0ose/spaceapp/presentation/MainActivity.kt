package com.go0ose.spaceapp.presentation

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.core.splashscreen.SplashScreen.Companion.installSplashScreen
import com.github.terrakok.cicerone.NavigatorHolder
import com.github.terrakok.cicerone.Router
import com.github.terrakok.cicerone.androidx.AppNavigator
import com.go0ose.spaceapp.R
import com.go0ose.spaceapp.SpaseApp
import com.go0ose.spaceapp.SpaseApp.Companion.appComponent
import com.go0ose.spaceapp.databinding.ActivityMainBinding
import com.go0ose.spaceapp.di.DaggerAppComponent
import com.go0ose.spaceapp.presentation.navigation.Screens
import com.go0ose.spaceapp.utils.isOnline
import javax.inject.Inject

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    private val navigator = AppNavigator(this, R.id.mainContainer)

    @Inject
    lateinit var navigatorHolder: NavigatorHolder

    @Inject
    lateinit var router: Router

    override fun onCreate(savedInstanceState: Bundle?) {
        val isOnline = isOnline()
        installSplashScreen().setKeepOnScreenCondition {
            !isOnline
        }
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        SpaseApp.initDagger(this)
        appComponent = DaggerAppComponent.builder()
            .buildContext(applicationContext)
            .build()
        SpaseApp.appComponent?.inject(this)

        navigatorHolder.setNavigator(navigator)
        router.newRootScreen(Screens.Main())

        initBottomNavigation()
    }

    private fun initBottomNavigation() {
        binding.bottomNavigation.itemIconTintList = null

        binding.bottomNavigation.setOnItemSelectedListener {
            when (it.itemId) {
                R.id.mainScreenFragment -> {
                    router.backTo(Screens.Main())
                }
                R.id.mapScreenFragment -> {
                    router.navigateTo(Screens.Map())
                }
            }
            true
        }
    }

    override fun onRestart() {
        super.onRestart()
        navigatorHolder.setNavigator(navigator)
    }

    override fun onPause() {
        navigatorHolder.removeNavigator()
        super.onPause()
    }
}