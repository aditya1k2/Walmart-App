package com.example.walmart.data.networkfactory

import com.example.walmart.model.Category
import com.example.walmart.model.CategoryListData
import com.example.walmart.model.RetroData
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface ApiInterface {
    @GET("v1/trends")
    fun getData(
        @Query("format") format: String = "json",
        @Query("apiKey") apiKey: String = "tcefd9shdkvmqnq2b6657ra8"
    ): Call<RetroData>


    @GET("v1/paginated/items")
    suspend fun getCategoryListData(
        @Query("format") format: String,
        @Query("category") category: String?,
        @Query("apiKey") apiKey: String,
        @Query("count") count: Int,
        @Query("lastDoc") lastDoc:String?,
        @Query("remainingHits") remainingHits:String?
    ): CategoryListData

    @GET("v1/paginated/items")
    suspend fun getCategoryListDataPagination(
        @Query("format") format: String,
        @Query("category") category: String?,
        @Query("apiKey") apiKey: String,
        @Query("count") count: Int,
        @Query("Path") path:String?
    ): CategoryListData
//    @Path("path") path: String


    @GET("v1/taxonomy")
    fun getCategory(
        @Query("format") format: String = "json",
        @Query("apiKey") apiKey: String = "tcefd9shdkvmqnq2b6657ra8"
    ): Call<Category>
}