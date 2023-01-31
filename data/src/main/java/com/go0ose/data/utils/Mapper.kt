package com.go0ose.data.utils

import com.go0ose.domain.models.Camera
import com.go0ose.domain.models.MarsPhotos
import com.go0ose.domain.models.Photo
import com.go0ose.domain.models.Rover
import com.go0ose.data.retrofit.MarsPhotosResponse
import io.reactivex.Observable

fun mapMarsPhotosResponse(response: Observable<MarsPhotosResponse>): Observable<MarsPhotos> {
    return response.map { marsPhotosResponse ->
        val photos = marsPhotosResponse.photos.map { photoResponse ->
            val camera = Camera(photoResponse.camera.id,
                photoResponse.camera.name,
                photoResponse.camera.roverId,
                photoResponse.camera.fullName)
            val rover = Rover(photoResponse.rover.id,
                photoResponse.rover.name,
                photoResponse.rover.landingDate,
                photoResponse.rover.launch_date,
                photoResponse.rover.status)
            Photo(photoResponse.id,
                photoResponse.sol,
                photoResponse.imgSrc,
                photoResponse.earthDate,
                camera,
                rover)
        }
        MarsPhotos(photos)
    }
}