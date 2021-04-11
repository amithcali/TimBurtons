package com.tim.burtons.data.api


import com.tim.burtons.BuildConfig
import com.tim.burtons.model.Product
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET
import retrofit2.http.Url

interface APIService {

    @GET("/v1/products")
    suspend fun getUsers(): List<Product>

    companion object {
       // private const val BASE_URL = "https://api.timburtons.com/"

        fun create(): APIService {
            val logger = HttpLoggingInterceptor().apply { level = HttpLoggingInterceptor.Level.BASIC }

            val client = OkHttpClient.Builder()
                .addInterceptor(logger)
                .build()

            return Retrofit.Builder()
                .baseUrl(BuildConfig.APP_BASE_URL)
                .client(client)
                .addConverterFactory(GsonConverterFactory.create())
                .build()
                .create(APIService::class.java)
        }
    }

}