package com.go0ose.data.retrofit

import com.google.gson.Gson
import com.google.gson.GsonBuilder
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory

object RetrofitClient {

    private const val BASE_URl = "https://api.nasa.gov/mars-photos/api/v1/rovers/curiosity/"
    const val SOL = 1000
    const val API_KEY = "crb0TOvc8HfgOiv9m2sqNlZ4Pf6ad0YGeMKIVgpS"

    private var gson: Gson = GsonBuilder()
        .setLenient()
        .create()

    private val httpClient = OkHttpClient.Builder()
        .addInterceptor(HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.BODY))
        .build()

    private fun getClient() = Retrofit.Builder()
        .baseUrl(BASE_URl)
        .client(httpClient)
        .addConverterFactory(GsonConverterFactory.create(gson))
        .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
        .build()

    fun getNasaApi(): NasaApi = getClient().create(NasaApi::class.java)
}
