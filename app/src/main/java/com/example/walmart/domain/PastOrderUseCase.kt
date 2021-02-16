package com.example.walmart.domain

import androidx.lifecycle.LiveData
import com.example.walmart.data.db.entities.PastOrder
import com.example.walmart.data.repository.WalmartRepository

class PastOrderUseCase(

    private val repository: WalmartRepository
) {
    fun getPastOrders(): LiveData<List<PastOrder>> {
        return repository.getPastOrders()
    }

}