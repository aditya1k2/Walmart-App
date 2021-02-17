package com.example.walmart.domain

import androidx.lifecycle.LiveData
import com.example.walmart.data.db.entities.ProductTable
import com.example.walmart.data.repository.WalmartRepository

class CartUseCase(
   private val repository: WalmartRepository
) {

    fun getCartDetails(): LiveData<List<ProductTable>> {
        return repository.getAllProduct()
    }

    suspend fun delete(product: ProductTable) {
        repository.delete(product)
    }

    fun cartSize() : LiveData<Int>{
        return repository.cartSize()
    }


}