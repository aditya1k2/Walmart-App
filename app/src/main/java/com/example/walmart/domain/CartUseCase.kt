package com.example.walmart.domain

import android.app.Application
import androidx.lifecycle.LiveData
import com.example.walmart.data.db.ProductDatabase
import com.example.walmart.data.db.entities.ProductTable
import com.example.walmart.data.repository.WalmartRepository

class CartUseCase {
    private val repository = WalmartRepository()
    fun getCartDetails(application: Application): LiveData<List<ProductTable>> {
        return repository.getAllProduct(application)
    }

    suspend fun delete(product: ProductTable, application: Application) {
        repository.delete(product,application)
    }


}