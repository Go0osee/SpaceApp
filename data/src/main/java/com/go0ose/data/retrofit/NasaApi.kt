package com.go0ose.data.retrofit

import com.go0ose.data.retrofit.RetrofitClient.API_KEY
import com.go0ose.data.retrofit.RetrofitClient.SOL
import io.reactivex.Observable
import retrofit2.http.GET
import retrofit2.http.Query

interface NasaApi {

        @GET("photos")
        fun getMarsPhotosFromApi(
            @Query("sol") sol: Int = SOL,
            @Query("page") page: Int,
            @Query("api_key") apiKey: String = API_KEY,
        ): Observable<MarsPhotosResponse>
}