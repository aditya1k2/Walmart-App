package com.example.walmart.data.repository

import android.app.Application
import androidx.lifecycle.LiveData
import com.example.walmart.data.DataSource.WalmartDataSource
import com.example.walmart.data.db.ProductDatabase
import com.example.walmart.data.db.entities.ProductTable
import com.example.walmart.model.CategoryId
import com.example.walmart.model.Items

class WalmartRepository {

    private val dataSource = WalmartDataSource()

    fun callApiForTrendingData(): LiveData<List<Items>> {
        return dataSource.callApiForTrendingData()
    }

    fun callApiForCategoryListData(
        catId: String?
    ): LiveData<List<Items>> {
        return dataSource.callApiForCategoryListData(catId)
    }

    suspend fun callApiForCategoryList(): LiveData<List<CategoryId>> {
        return dataSource.callApiForCategoryList()
    }

    //    fun getDatabase(application : Application){
//        val dao = ProductDatabase.getDatabase(application).getProductDao()
//    }

    suspend fun insert(product: ProductTable, application: Application) {
        val productDao = ProductDatabase.getDatabase(application).getProductDao()
        productDao.insert(product)
    }

    suspend fun delete(product: ProductTable, application: Application) {
        val productDao = ProductDatabase.getDatabase(application).getProductDao()
        productDao.delete(product)
    }

    fun getAllProduct(application: Application): LiveData<List<ProductTable>> {
        val productDao = ProductDatabase.getDatabase(application).getProductDao()
        return productDao.getAllProduct()
    }


}