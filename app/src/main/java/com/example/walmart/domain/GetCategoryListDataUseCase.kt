package com.example.walmart.domain

import android.util.Log
import androidx.lifecycle.LiveData
import com.example.walmart.model.Items
import com.example.walmart.data.repository.WalmartRepository
import com.example.walmart.model.CategoryListData
import com.facebook.shimmer.ShimmerFrameLayout

class GetCategoryListDataUseCase {
    private val repository = WalmartRepository()
    suspend fun getCategoryListData(catId: String?): CategoryListData {
        Log.d("SwipeCheck", "In GetTrendingListData UseCase")
        return repository.callApiForCategoryListData(catId)
    }

    suspend fun callApiForCategoryListDataPagination(
        catId: String?,
        lastDoc: String?,
        remainingHits:String?
    ): CategoryListData {
        Log.d("Pagination", "GetCategoryListDataUseCase")

        return repository.callApiForCategoryListDataPagination(catId, lastDoc,remainingHits)
    }

}