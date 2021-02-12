package com.example.walmart.data.DataSource

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Observer
import com.example.walmart.data.networkfactory.ApiInterface
import com.example.walmart.data.networkfactory.RetrofitFactory
import com.example.walmart.model.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import kotlin.coroutines.coroutineContext

class WalmartDataSource {

    fun callApiForTrendingData(): MutableLiveData<List<Items>> {
        val data: MutableLiveData<List<Items>> = MutableLiveData()
        val retrofit = RetrofitFactory().getRetrofit()
        retrofit.create(ApiInterface::class.java)
            .getData()
            .enqueue(object : Callback<RetroData> {
                override fun onFailure(call: Call<RetroData>, t: Throwable) {
                    Log.e("WalmartViewModelFail", t.localizedMessage)
                }

                override fun onResponse(call: Call<RetroData>, response: Response<RetroData>) {
                    if (response.isSuccessful) {
                        response.body()?.items?.let {
                            data.value = it
                        }
//                        Log.d("WalmartiewModelSuccess", data.toString())
                    }
                }
            })

        return data
    }

    suspend fun callApiForCategoryListData(
        catId: String?
    ): CategoryListData {
        val data: MutableLiveData<CategoryListData> = MutableLiveData()
        val retrofit = RetrofitFactory().getRetrofit()

        return RetrofitFactory().getRetrofit().create(ApiInterface::class.java)
            .getCategoryListData("json", catId, "tcefd9shdkvmqnq2b6657ra8", 20, "","")

//        retrofit.create(ApiInterface::class.java)
//            .getCategoryListData("json", catId, "tcefd9shdkvmqnq2b6657ra8", 20,"")
//            .enqueue(object : Callback<CategoryListData> {
//                override fun onFailure(call: Call<CategoryListData>, t: Throwable) {
//                    Log.e("WalmartViewModelFail", t.localizedMessage)
//                }
//
//                override fun onResponse(
//                    call: Call<CategoryListData>,
//                    response: Response<CategoryListData>
//                ) {
//                    if (response.isSuccessful) {
//
//                        response.body()?.let {
//                            data.value = it
//                        }
//                        Log.d("WalmartViewModelSuccess", data.toString())
//                    }
//                }
//            })
//
//        return data
    }

    suspend fun callApiForCategoryListDataPagination(
        catId: String?,
        lastDoc: String?,
        remainingHits:String?

    ): CategoryListData {
        var data: CategoryListData?


        return RetrofitFactory().getRetrofit().create(ApiInterface::class.java)
            .getCategoryListData("json", catId, "tcefd9shdkvmqnq2b6657ra8", 20, lastDoc,remainingHits)


//        retrofit.create(ApiInterface::class.java)
//            .getCategoryListDataPagination("json", catId, "tcefd9shdkvmqnq2b6657ra8", 20, path)
//            .enqueue(object : Callback<CategoryListData> {
//                override fun onFailure(call: Call<CategoryListData>, t: Throwable) {
//                    Log.d("Pagination", "WalMart DataSource onFailure")
//
//                }
//
//                override fun onResponse(
//                    call: Call<CategoryListData>,
//                    response: Response<CategoryListData>
//                ) {
//                    if (response.isSuccessful) {
//                        response.body().let {
//                            data = it
//                            Log.d(
//                                "Pagination",
//                                "WalMart DataSource OnResponse+${data.toString()}"
//                            )
//                        }
//                    }
//                }
//            })

//       return data

    }


    suspend fun callApiForCategoryList(): LiveData<List<CategoryId>> {
        val data: MutableLiveData<List<CategoryId>> = MutableLiveData()
        val retrofit = RetrofitFactory().getRetrofit()
        retrofit.create(ApiInterface::class.java)
            .getCategory()
            .enqueue(object : Callback<Category> {
                override fun onFailure(call: Call<Category>, t: Throwable) {
                    Log.e("WalmartViewModelFail", t.localizedMessage)
                }

                override fun onResponse(call: Call<Category>, response: Response<Category>) {
                    if (response.isSuccessful) {
                        response.body()?.categories?.let {
                            data.value = it
                        }
//                        Log.d("WalmartiewModelSuccess", data.toString())
                    }
                }
            })
        return data
    }


}