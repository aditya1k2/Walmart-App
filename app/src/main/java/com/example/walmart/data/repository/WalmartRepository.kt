package com.example.walmart.data.repository

import android.util.Log
import androidx.lifecycle.LiveData
import com.example.walmart.data.DataSource.WalmartDataSource
import com.example.walmart.data.db.ProductDatabase
import com.example.walmart.data.db.dao.ProductDao
import com.example.walmart.data.db.entities.PastOrder
import com.example.walmart.data.db.entities.ProductTable
import com.example.walmart.model.CategoryId
import com.example.walmart.model.CategoryListData
import com.example.walmart.util.App

class WalmartRepository(
    private val dataSource: WalmartDataSource,
    private val productDao: ProductDao
) {

//    private val productDao = ProductDatabase.getDatabase(App.getAppContext()).getProductDao()

    suspend fun callApiForCategoryListData(
        catId: String?
    ): CategoryListData {
        return dataSource.callApiForCategoryListData(catId)
    }

    suspend fun callApiForCategoryListDataPagination(
        catId: String?,
        lastDoc: String?,
        remainingHits: String?
    ): CategoryListData {
        Log.d("Pagination", "WalMartRepository")

        return dataSource
            .callApiForCategoryListDataPagination(
                catId,
                lastDoc,
                remainingHits
            )
    }

    suspend fun callApiForCategoryList(): List<CategoryId> {
        return dataSource.callApiForCategoryList()
    }


    suspend fun insert(product: ProductTable) {
        productDao.insert(product)
    }

    suspend fun insertOrder(product: PastOrder) {
        productDao.insertOrder(product)
    }

    suspend fun delete(product: ProductTable) {
        productDao.delete(product)
    }

    fun getAllProduct(): LiveData<List<ProductTable>> {
        return productDao.getAllProduct()
    }

    fun getPastOrders(): LiveData<List<PastOrder>> {
        return productDao.getPastOrder()
    }

    fun cartSize(): LiveData<Int>{
        return productDao.cartSize()
    }



}