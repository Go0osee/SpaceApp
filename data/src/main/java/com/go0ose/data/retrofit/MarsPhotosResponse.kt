package com.go0ose.data.retrofit

import com.google.gson.annotations.SerializedName

data class MarsPhotosResponse(
    @SerializedName("photos")
    val photos: List<PhotoResponse>
)

data class PhotoResponse(
    @SerializedName("id")
    val id: Int,
    @SerializedName("sol")
    val sol: Int,
    @SerializedName("img_src")
    val imgSrc: String,
    @SerializedName("earth_date")
    val earthDate: String,
    @SerializedName("camera")
    val camera: CameraResponse,
    @SerializedName("rover")
    val rover: RoverResponse
)

data class CameraResponse(
    @SerializedName("id")
    val id: Int,
    @SerializedName("name")
    val name: String,
    @SerializedName("rover_id")
    val roverId: Int,
    @SerializedName("full_name")
    val fullName: String
)

data class RoverResponse(
    @SerializedName("id")
    val id: Int,
    @SerializedName("name")
    val name: String,
    @SerializedName("landing_date")
    val landingDate: String,
    @SerializedName("launch_date")
    val launch_date: String,
    @SerializedName("status")
    val status: String
)
