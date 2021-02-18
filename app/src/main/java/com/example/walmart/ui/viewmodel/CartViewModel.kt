package com.example.walmart.ui.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.walmart.data.db.entities.PastOrder
import com.example.walmart.data.db.entities.ProductTable
import com.example.walmart.module.WalmartModule
import kotlinx.coroutines.launch

class CartViewModel : ViewModel() {


    fun getCartDetails(): LiveData<List<ProductTable>> {
        return WalmartModule.cartUseCase.getCartDetails()
    }

    fun deleteProduct(product: ProductTable) = viewModelScope.launch {
        WalmartModule.cartUseCase.delete(product)
    }


    fun insertOrder(product: PastOrder) = viewModelScope.launch {
        WalmartModule.repository.insertOrder(product)
    }






}