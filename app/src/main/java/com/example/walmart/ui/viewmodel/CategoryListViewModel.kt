package com.example.walmart.ui.viewmodel

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.walmart.model.CategoryListData
import com.example.walmart.module.WalmartModule
import kotlinx.coroutines.launch

class CategoryListViewModel : ViewModel() {

    private val _categoryListData = MutableLiveData<CategoryListData>()
    val categoryListData: LiveData<CategoryListData>
        get() = _categoryListData

    fun getCategoryListData(catId: String?) {
        viewModelScope.launch {
            _categoryListData.value = WalmartModule.getCategoryListDataUseCase.getCategoryListData(catId)
        }
    }

    fun getCategoryListDataPagination(
        catId: String?,
        lastDoc: String?,
        remainingHits: String?
    ) {
        viewModelScope.launch {
            _categoryListData.value =
                WalmartModule.getCategoryListDataUseCase.callApiForCategoryListDataPagination(
                    catId,
                    lastDoc,
                    remainingHits
                )
            Log.d("Pagination", "WalMartViewModel")
            Log.d("Pagination", _categoryListData.value.toString())
        }

    }


}

















