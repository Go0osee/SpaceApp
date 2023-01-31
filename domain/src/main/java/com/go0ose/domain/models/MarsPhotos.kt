package com.go0ose.domain.models

data class MarsPhotos(
    val photos: List<Photo>
)

data class Photo(
    val id: Int,
    val sol: Int,
    val imgSrc: String,
    val earthDate: String,
    val camera: Camera,
    val rover: Rover
)

data class Camera(
    val id: Int,
    val name: String,
    val roverId: Int,
    val fullName: String
)

data class Rover(
    val id: Int,
    val name: String,
    val landingDate: String,
    val launch_date: String,
    val status: String
)