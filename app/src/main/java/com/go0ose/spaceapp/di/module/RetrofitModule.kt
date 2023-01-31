package com.go0ose.spaceapp.di.module

import com.go0ose.data.retrofit.NasaApi
import com.go0ose.data.retrofit.RetrofitClient
import dagger.Module
import dagger.Provides

@Module
class RetrofitModule {

    @Provides
    fun provideNasaApi(): NasaApi {
        return RetrofitClient.getNasaApi()
    }
}