package com.example.walmart.data.networkfactory

import com.example.walmart.model.Category
import com.example.walmart.model.CategoryListData
import retrofit2.http.GET
import retrofit2.http.Query

interface ApiInterface {


    @GET("v1/paginated/items")
    suspend fun getCategoryListData(
        @Query("format") format: String,
        @Query("category") category: String?,
        @Query("apiKey") apiKey: String,
        @Query("count") count: Int,
        @Query("lastDoc") lastDoc: String?,
        @Query("remainingHits") remainingHits: String?
    ): CategoryListData


    @GET("v1/taxonomy")
    suspend fun getCategory(
        @Query("format") format: String,
        @Query("apiKey") apiKey: String
    ): Category
}