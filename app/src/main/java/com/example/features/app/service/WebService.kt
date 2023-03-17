package com.example.features.app.service

import com.google.gson.GsonBuilder
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object WebService {

    const val URL_GITHUB = "https://api.github.com/"

    private val gson by lazy { GsonBuilder().create() }

    private val okHttp by lazy {
        OkHttpClient.Builder()
            .build()
    }

    fun retrofit(url: String): Retrofit = Retrofit.Builder()
        .addConverterFactory(GsonConverterFactory.create(gson))
        .baseUrl(url)
        .client(okHttp)
        .build()

    inline fun <reified T> service(url: String): T = retrofit(url).create(
        T::class.java
    )
}