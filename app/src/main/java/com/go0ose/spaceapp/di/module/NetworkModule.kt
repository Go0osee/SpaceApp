package com.go0ose.spaceapp.di.module

import com.go0ose.domain.repository.NetworkRepository
import com.go0ose.data.repository.NetworkRepositoryImpl
import dagger.Binds
import dagger.Module

@Module
interface NetworkModule {

    @Binds
    fun bindNetworkRepository(networkRepository: NetworkRepositoryImpl) : NetworkRepository
}