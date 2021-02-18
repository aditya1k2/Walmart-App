package com.example.walmart.ui.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.walmart.model.CategoryId
import com.example.walmart.module.WalmartModule
import kotlinx.coroutines.launch

class HomeViewModel : ViewModel() {

    private val _categoryListData = MutableLiveData<List<CategoryId>>()
    val categoryListData: LiveData<List<CategoryId>>
        get() = _categoryListData

    fun getCategoryList() = viewModelScope.launch {
        _categoryListData.value = WalmartModule.getCategoryListUseCase.getCategoryList()
    }

}