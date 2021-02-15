package com.example.walmart.ui.viewmodel

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.walmart.domain.GetCategoryListDataUseCase
import com.example.walmart.model.CategoryListData
import kotlinx.coroutines.launch

class CategoryListViewModel : ViewModel() {

    //to do- use backing property
    private val _categoryListData = MutableLiveData<CategoryListData>()
    val categoryListData: LiveData<CategoryListData>
        get() = _categoryListData


//    var categoryListDataPagination = MutableLiveData<CategoryListData>()


    fun getCategoryListData(catId: String?) {
        viewModelScope.launch {
            _categoryListData.value = GetCategoryListDataUseCase().getCategoryListData(catId)
        }
    }

    fun getCategoryListDataPagination(
        catId: String?,
        lastDoc: String?,
        remainingHits: String?
    ) {
        viewModelScope.launch {
            _categoryListData.value =
                GetCategoryListDataUseCase().callApiForCategoryListDataPagination(
                    catId,
                    lastDoc,
                    remainingHits
                )
            Log.d("Pagination", "WalMartViewModel")
            Log.d("Pagination", _categoryListData.value.toString())
        }

    }
}

















