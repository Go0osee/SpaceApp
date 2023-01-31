package com.go0ose.spaceapp.di.module

import com.github.terrakok.cicerone.Cicerone
import com.github.terrakok.cicerone.NavigatorHolder
import com.github.terrakok.cicerone.Router
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class NavigationModule {

    @Singleton
    @Provides
    fun getCicerone(): Cicerone<Router> = Cicerone.create()

    @Singleton
    @Provides
    fun getNavigationHolder(cicerone: Cicerone<Router>): NavigatorHolder = cicerone.getNavigatorHolder()

    @Singleton
    @Provides
    fun getRouter(cicerone: Cicerone<Router>): Router = cicerone.router
}

