package com.example.walmart.ui.viewmodel

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.walmart.domain.GetCategoryListDataUseCase
import com.example.walmart.model.CategoryListData
import com.example.walmart.model.Items
import com.facebook.shimmer.ShimmerFrameLayout
import kotlinx.coroutines.launch

class WalmartViewModel : ViewModel() {

    //to do- use backing property
    var categoryListData = MutableLiveData<CategoryListData>()
//    var categoryListDataPagination = MutableLiveData<CategoryListData>()


    fun getCategoryListData(catId: String?) {
        viewModelScope.launch {
            categoryListData.value = GetCategoryListDataUseCase().getCategoryListData(catId)
        }

    }

    fun getCategoryListDataPagination(
        catId: String?,
        lastDoc: String?,
        remainingHits: String?
    ) {
        viewModelScope.launch {
            categoryListData.value =
                GetCategoryListDataUseCase().callApiForCategoryListDataPagination(catId, lastDoc,remainingHits)
            Log.d("Pagination", "WalMartViewModel")
            Log.d("Pagination", categoryListData.value.toString())
        }

    }
}

















