package com.example.walmart.data.DataSource

import com.example.walmart.data.networkfactory.ApiInterface
import com.example.walmart.model.CategoryId
import com.example.walmart.model.CategoryListData

class WalmartDataSource(
    private val apiInterface: ApiInterface
) {


    suspend fun callApiForCategoryListData(
        catId: String?
    ): CategoryListData {

        return apiInterface
            .getCategoryListData(
                "json",
                catId,
                "tcefd9shdkvmqnq2b6657ra8",
                20,
                "",
                ""
            )
    }

    suspend fun callApiForCategoryListDataPagination(
        catId: String?,
        lastDoc: String?,
        remainingHits: String?

    ): CategoryListData {
        return apiInterface
            .getCategoryListData(
                "json",
                catId,
                "tcefd9shdkvmqnq2b6657ra8",
                20,
                lastDoc,
                remainingHits
            )
    }


    suspend fun callApiForCategoryList(): List<CategoryId> {
        return apiInterface
            .getCategory("json", "tcefd9shdkvmqnq2b6657ra8").categories
    }


}