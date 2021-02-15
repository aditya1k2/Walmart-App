package com.example.walmart.ui.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.walmart.data.db.entities.PastOrder
import com.example.walmart.data.db.entities.ProductTable
import com.example.walmart.data.repository.WalmartRepository
import kotlinx.coroutines.launch

class ItemDetailViewModel : ViewModel() {

    private val repository = WalmartRepository()
    fun insertProduct(product: ProductTable) = viewModelScope.launch {
        repository.insert(product)
    }

    fun insertOrder(product: PastOrder) = viewModelScope.launch {
        repository.insertOrder(product)
    }

}