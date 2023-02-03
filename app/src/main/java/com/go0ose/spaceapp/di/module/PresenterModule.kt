package com.go0ose.spaceapp.di.module

import android.content.Context
import com.github.terrakok.cicerone.Router
import com.go0ose.domain.usecase.GetMarsPhotoFromApi
import com.go0ose.spaceapp.presentation.screens.details.DetailsPresenter
import com.go0ose.spaceapp.presentation.screens.main.MainPresenter
import com.go0ose.spaceapp.presentation.screens.map.MapPresenter
import dagger.Module
import dagger.Provides

@Module
class PresenterModule {

    @Provides
    fun getMainPresenter(
        router: Router,
        getMarsPhotoFromApi: GetMarsPhotoFromApi,
        context: Context,
    ): MainPresenter =
        MainPresenter(getMarsPhotoFromApi, router, context)

    @Provides
    fun getMapPresenter(): MapPresenter = MapPresenter()

    @Provides
    fun getDetailsPresenter(
        router: Router,
        context: Context,
    ): DetailsPresenter = DetailsPresenter(router, context)
}