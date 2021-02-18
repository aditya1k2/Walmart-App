package com.example.walmart.ui.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.example.walmart.data.db.entities.PastOrder
import com.example.walmart.module.WalmartModule

class PastOrderViewModel : ViewModel() {

    fun getPastOrders(): LiveData<List<PastOrder>> {
        return WalmartModule.pastOrderUseCase.getPastOrders()
    }

    fun cartSize():LiveData<Int>{
        return WalmartModule.pastOrderUseCase.cartSize()
    }
}