package com.example.walmart.ui.viewmodel

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.walmart.domain.GetCategoryListDataUseCase
import com.example.walmart.model.Items
import com.facebook.shimmer.ShimmerFrameLayout

class WalmartViewModel : ViewModel() {

    //to do- use backing property
    var categoryListData: LiveData<List<Items>> = MutableLiveData()
    fun getCategoryListData(catId: String?) {
        categoryListData = GetCategoryListDataUseCase().getCategoryListData(catId)
        Log.d("SwipeCheck", "In GetTrendingListData")
        Log.d("SwipeCheck", categoryListData.value.toString())
    }
}

















