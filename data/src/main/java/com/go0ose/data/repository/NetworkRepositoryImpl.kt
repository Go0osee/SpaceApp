package com.go0ose.data.repository

import com.go0ose.domain.repository.NetworkRepository
import com.go0ose.domain.models.MarsPhotos
import com.go0ose.data.utils.mapMarsPhotosResponse
import com.go0ose.data.retrofit.NasaApi
import io.reactivex.Observable
import javax.inject.Inject

class NetworkRepositoryImpl @Inject constructor(
    private val nasaApi: NasaApi,
) : NetworkRepository {
    override fun getMarsPhotosFromApi(page: Int): Observable<MarsPhotos> {
        return mapMarsPhotosResponse(nasaApi.getMarsPhotosFromApi(page = page))
    }
}