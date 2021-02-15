package com.example.walmart.data.networkfactory

import com.example.walmart.module.WalmartModule
import retrofit2.Retrofit

class RetrofitFactory {
    fun getRetrofit(): Retrofit {
        return WalmartModule.retrofitService
    }
}