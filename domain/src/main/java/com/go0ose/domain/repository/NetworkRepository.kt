package com.go0ose.domain.repository

import com.go0ose.domain.models.MarsPhotos
import io.reactivex.Observable

interface NetworkRepository {
    fun getMarsPhotosFromApi(page: Int): Observable<MarsPhotos>
}