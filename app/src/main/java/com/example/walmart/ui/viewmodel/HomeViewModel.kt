package com.example.walmart.ui.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.walmart.domain.GetCategoryListUseCase
import com.example.walmart.model.CategoryId
import com.example.walmart.module.WalmartModule
import kotlinx.coroutines.launch

class HomeViewModel : ViewModel() {
    var categoryListData = MutableLiveData<List<CategoryId>>()

    fun getCategoryList() = viewModelScope.launch {
        categoryListData.value = WalmartModule.getCategoryListUseCase.getCategoryList()
    }
}