package com.example.walmart.domain

import android.util.Log
import androidx.lifecycle.LiveData
import com.example.walmart.model.Items
import com.example.walmart.data.repository.WalmartRepository
import com.facebook.shimmer.ShimmerFrameLayout

class GetCategoryListDataUseCase {
    private val repository = WalmartRepository()
    fun getCategoryListData(catId: String?): LiveData<List<Items>> {
        Log.d("SwipeCheck", "In GetTrendingListData UseCase")
        return repository.callApiForCategoryListData(catId)
    }
}