package com.example.walmart.data.DataSource

import com.example.walmart.data.networkfactory.ApiInterface
import com.example.walmart.data.networkfactory.RetrofitFactory
import com.example.walmart.model.CategoryId
import com.example.walmart.model.CategoryListData

object WalmartDataSource {

    suspend fun callApiForCategoryListData(
        catId: String?
    ): CategoryListData {

        return RetrofitFactory().getRetrofit().create(ApiInterface::class.java)
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

        return RetrofitFactory().getRetrofit().create(ApiInterface::class.java)
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
        return RetrofitFactory()
            .getRetrofit()
            .create(ApiInterface::class.java)
            .getCategory("json", "tcefd9shdkvmqnq2b6657ra8").categories


    }


}