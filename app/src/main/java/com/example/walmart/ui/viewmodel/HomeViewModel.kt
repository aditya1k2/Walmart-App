package com.example.walmart.ui.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.walmart.domain.GetCategoryListUseCase
import com.example.walmart.model.CategoryId
import com.facebook.shimmer.ShimmerFrameLayout
import kotlinx.coroutines.launch

class HomeViewModel : ViewModel() {

    var categoryListData: LiveData<List<CategoryId>> = MutableLiveData()
    fun getCategoryList() = viewModelScope.launch {
        categoryListData = GetCategoryListUseCase().getCategoryList()
    }
}