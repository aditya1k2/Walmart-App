package com.example.walmart.domain

import androidx.lifecycle.LiveData
import com.example.walmart.data.repository.WalmartRepository

class BaseToolBarActivityUseCase(
    private val repository: WalmartRepository
) {
    fun cartSize() : LiveData<Int> {
        return repository.cartSize()
    }

}