package com.example.walmart.ui.viewmodel

import android.app.Application
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.walmart.data.db.entities.ProductTable
import com.example.walmart.data.repository.WalmartRepository
import com.example.walmart.domain.CartUseCase
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch

class CartViewModel : ViewModel() {

    private val cartUseCase = CartUseCase()
    fun getCartDetails(application: Application): LiveData<List<ProductTable>> {
        return cartUseCase.getCartDetails(application)
    }
    fun deleteProduct(product: ProductTable, application: Application) = viewModelScope.launch {
        cartUseCase.delete(product, application)
    }






}