package com.example.healthwiser.network

import com.example.healthwiser.util.Constants.Companion.BASE_URL
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import retrofit2.create

class RetrofitInstance {
    companion object {

        //builder
        private val retrofit by lazy {
            val logging = HttpLoggingInterceptor() //track request and responses
                .setLevel(HttpLoggingInterceptor.Level.BODY) //see response body
            val client = OkHttpClient.Builder()
                .addInterceptor(logging)
                .build()
            Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(MoshiConverterFactory.create())
                .client(client)
                .build()
        }

        //used to make network requests
        val api: HealthApi by lazy {
            retrofit.create(HealthApi::class.java)
        }
    }
}