package com.example.walmart.data.db.dao

import androidx.lifecycle.LiveData
import androidx.room.*
import com.example.walmart.data.db.entities.ProductTable

@Dao
interface ProductDao {

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insert(product: ProductTable)

    @Delete
    suspend fun delete(product: ProductTable)

    @Query("Select * from product_table order by id ASC")
    fun getAllProduct(): LiveData<List<ProductTable>>


}