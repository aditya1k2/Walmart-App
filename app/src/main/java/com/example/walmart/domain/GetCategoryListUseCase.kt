package com.example.walmart.domain

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.walmart.model.CategoryId
import com.example.walmart.data.repository.WalmartRepository
import com.facebook.shimmer.ShimmerFrameLayout

class GetCategoryListUseCase {

    private val repository = WalmartRepository()
   suspend fun getCategoryList(): LiveData<List<CategoryId>> {
        Log.d("SwipeCheck","In GetTrendingListData UseCase")
        return repository.callApiForCategoryList()
    }




}