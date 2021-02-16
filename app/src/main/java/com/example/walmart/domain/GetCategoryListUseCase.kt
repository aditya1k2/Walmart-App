package com.example.walmart.domain

import android.util.Log
import com.example.walmart.data.repository.WalmartRepository
import com.example.walmart.model.CategoryId

class GetCategoryListUseCase(
    private val repository: WalmartRepository

) {
    suspend fun getCategoryList(): List<CategoryId> {

        Log.d("SwipeCheck", "In GetTrendingListData UseCase")
        return repository.callApiForCategoryList()
    }
}