package com.example.walmart.domain

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.walmart.model.Items
import com.example.walmart.data.repository.WalmartRepository

class GetTrendingListUseCase {

    private val repository = WalmartRepository()
    fun getTrendingListData(): LiveData<List<Items>> {
        Log.d("SwipeCheck","In GetTrendingListData UseCase")
        return repository.callApiForTrendingData()
    }
}