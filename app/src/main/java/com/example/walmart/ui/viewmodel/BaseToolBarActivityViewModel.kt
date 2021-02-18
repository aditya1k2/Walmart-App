package com.example.walmart.ui.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.example.walmart.module.WalmartModule

class BaseToolBarActivityViewModel : ViewModel() {

    val cartCount: LiveData<Int> = cartSize()

    fun cartSize(): LiveData<Int> {
        return WalmartModule.cartUseCase.cartSize()
    }
}