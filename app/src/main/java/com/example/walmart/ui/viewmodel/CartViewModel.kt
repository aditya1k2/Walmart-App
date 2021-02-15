package com.example.walmart.ui.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.walmart.data.db.entities.PastOrder
import com.example.walmart.data.db.entities.ProductTable
import com.example.walmart.data.repository.WalmartRepository
import com.example.walmart.domain.CartUseCase
import kotlinx.coroutines.launch

class CartViewModel : ViewModel() {

    private val cartUseCase = CartUseCase()
    private val repository = WalmartRepository()
    fun getCartDetails(): LiveData<List<ProductTable>> {
        return cartUseCase.getCartDetails()
    }

    fun deleteProduct(product: ProductTable) = viewModelScope.launch {
        cartUseCase.delete(product)
    }


    fun insertOrder(product: PastOrder) = viewModelScope.launch {
        repository.insertOrder(product)
    }


}