package com.example.walmart.ui.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.example.walmart.data.db.entities.PastOrder
import com.example.walmart.domain.PastOrderUseCase

class PastOrderViewModel : ViewModel() {

    private val pastOrderUseCase = PastOrderUseCase()

    fun getPastOrders(): LiveData<List<PastOrder>> {
        return pastOrderUseCase.getPastOrders()
    }
}