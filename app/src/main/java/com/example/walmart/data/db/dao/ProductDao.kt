package com.example.walmart.data.db.dao

import androidx.lifecycle.LiveData
import androidx.room.*
import com.example.walmart.data.db.entities.PastOrder
import com.example.walmart.data.db.entities.ProductTable

@Dao
interface ProductDao {

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insert(product: ProductTable)

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insertOrder(product: PastOrder)

    @Delete
    suspend fun delete(product: ProductTable)

    @Query("Select * from product_table order by id ASC")
    fun getAllProduct(): LiveData<List<ProductTable>>

    @Query("Select * from past_order_table order by id ASC")
    fun getPastOrder(): LiveData<List<PastOrder>>

    @Query("SELECT COUNT(id) FROM product_table")
    fun cartSize() : LiveData<Int>
}