package com.example.walmart.ui.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.walmart.data.db.entities.PastOrder
import com.example.walmart.data.db.entities.ProductTable
import com.example.walmart.data.repository.WalmartRepository
import com.example.walmart.module.WalmartModule
import kotlinx.coroutines.launch

class ItemDetailViewModel : ViewModel() {


    fun insertProduct(product: ProductTable) = viewModelScope.launch {
        WalmartModule.repository.insert(product)
    }

    fun insertOrder(product: PastOrder) = viewModelScope.launch {
        WalmartModule.repository.insertOrder(product)
    }

}