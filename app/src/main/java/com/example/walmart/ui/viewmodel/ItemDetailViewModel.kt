package com.example.walmart.ui.viewmodel

import android.app.Application
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.walmart.data.db.entities.ProductTable
import com.example.walmart.data.repository.WalmartRepository
import kotlinx.coroutines.launch

class ItemDetailViewModel : ViewModel() {

    fun insertProduct(product: ProductTable) = viewModelScope.launch {
        WalmartRepository().insert(product)
    }
}