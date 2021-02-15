package com.example.walmart.data.db.entities

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "past_order_table")
class PastOrder(
    @ColumnInfo(name = "name") val productName: String?,
    @ColumnInfo(name = "price") val productPrice: Double,
    @ColumnInfo(name = "brand") val brand: String?,
    @ColumnInfo(name = "rating") val rating: Float,
    @ColumnInfo(name = "imageUrl") val imageUrl: String?
) {
    @PrimaryKey(autoGenerate = true)
    var id: Int = 0
}