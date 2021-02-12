package com.example.walmart.data.networkfactory

import android.util.Log
import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import retrofit2.create

class RetrofitFactory {
    private val baseURL = "https://api.walmartlabs.com/"
    fun getRetrofit(): Retrofit {
        Log.d("Pagination", "In Retrofit factory")

        return Retrofit.Builder()
            .baseUrl(baseURL)
            .addConverterFactory(
                MoshiConverterFactory.create(
                    Moshi.Builder()
                        .add(KotlinJsonAdapterFactory())
                        .build()
                )
            )
            .build()
    }
}