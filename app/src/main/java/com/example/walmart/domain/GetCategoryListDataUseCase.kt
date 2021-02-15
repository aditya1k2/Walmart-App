package com.example.walmart.domain

import android.util.Log
import com.example.walmart.data.repository.WalmartRepository
import com.example.walmart.model.CategoryListData

class GetCategoryListDataUseCase {
    private val repository = WalmartRepository()
    suspend fun getCategoryListData(catId: String?): CategoryListData {
        Log.d("SwipeCheck", "In GetTrendingListData UseCase")
        return repository.callApiForCategoryListData(catId)
    }

    suspend fun callApiForCategoryListDataPagination(
        catId: String?,
        lastDoc: String?,
        remainingHits: String?
    ): CategoryListData {
        Log.d("Pagination", "GetCategoryListDataUseCase")

        return repository.callApiForCategoryListDataPagination(
            catId,
            lastDoc,
            remainingHits
        )
    }

}