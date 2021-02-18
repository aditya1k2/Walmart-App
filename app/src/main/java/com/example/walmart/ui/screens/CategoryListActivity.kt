package com.example.walmart.ui.screens

import android.content.Context
import android.net.ConnectivityManager
import android.net.NetworkCapabilities
import android.net.Uri
import android.os.Build
import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.walmart.R
import com.example.walmart.ui.adapter.RvCategoryListAdapter
import com.example.walmart.ui.viewmodel.CategoryListViewModel
import kotlinx.android.synthetic.main.activity_category_list.*


class CategoryListActivity : BaseToolBarActivity() {
    private val viewModel by lazy {
        ViewModelProvider(this).get(CategoryListViewModel::class.java)
    }

    private var flag: Boolean = false
    private var isLoading: Boolean = true
    private var scrollOut: Int = 0
    private var visibleItemCount: Int = 0
    private var totalItemCount: Int = 0

    companion object {
        private const val BUFFER_COUNT: Int = 5

    }

    private val manager = LinearLayoutManager(this)
    val adapter = RvCategoryListAdapter()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_category_list)

        val categoryId: String? = intent.getStringExtra("categoryId")

        recyclerView.layoutManager = manager
        recyclerView.adapter = adapter

        viewModel.getCategoryListData(categoryId)
        viewModel.categoryListData.observe(this, Observer {
            shimmer_view_container.stopShimmerAnimation()
            shimmer_view_container.visibility = View.GONE
            flag = true
            Log.d("Pagination", "Observe")
            isLoading = true
            if (progressBar.visibility == View.VISIBLE)
                progressBar.visibility = View.GONE

            adapter.updateData(it.items)
        })


        recyclerView.addOnScrollListener(object : RecyclerView.OnScrollListener() {
            override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {

                super.onScrolled(recyclerView, dx, dy)
                visibleItemCount = manager.childCount
                totalItemCount = manager.itemCount
                scrollOut = manager.findFirstVisibleItemPosition()

                val uri: Uri = Uri.parse(viewModel.categoryListData.value?.nextPage)
                val lastDoc: String? = uri.getQueryParameter("lastDoc")
                val remainingHits: String? = uri.getQueryParameter("remainingHits")

                Log.d("Pagination", "Last Doc:- $lastDoc")
                Log.d("Pagination", "Remaining Hits:- $remainingHits")

                Log.d("Pagination", "Cat Id:- $categoryId")
                if (dy > 0) {
                    if (isNetworkAvailable(this@CategoryListActivity)) {
                        Log.d("Pagination", "Scrolled")
                        if (isLoading && (visibleItemCount + scrollOut == totalItemCount - BUFFER_COUNT)) {

                            progressBar.visibility = View.VISIBLE
                            isLoading = false
                            viewModel.getCategoryListDataPagination(
                                categoryId,
                                lastDoc,
                                remainingHits
                            )

                        }

                    } else {
                        Log.d("Network", "Retry")
                    }


                }
            }
        })
    }

    override fun onResume() {
        if (!flag) {
            shimmer_view_container.visibility = View.VISIBLE
            shimmer_view_container.startShimmerAnimation()
        }
        super.onResume()
    }

    override fun isCartOn(): Boolean {
        return true
    }

    override fun isPastOrderOn(): Boolean {
        return true
    }

    override fun isBackOn(): Boolean {
        return true
    }

    override fun activityName(): String? {
        return intent.getStringExtra("categoryName")
    }

    override fun onPause() {
        shimmer_view_container.apply {
            stopShimmerAnimation()
            visibility = View.GONE
        }
        super.onPause()
    }

    fun isNetworkAvailable(context: Context): Boolean {
        val connectivityManager =
            context.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.Q) {
            val capabilities =
                connectivityManager.getNetworkCapabilities(connectivityManager.activeNetwork)
            if (capabilities != null) {
                when {
                    capabilities.hasTransport(NetworkCapabilities.TRANSPORT_CELLULAR) -> {
                        return true
                    }
                    capabilities.hasTransport(NetworkCapabilities.TRANSPORT_WIFI) -> {
                        return true
                    }
                    capabilities.hasTransport(NetworkCapabilities.TRANSPORT_ETHERNET) -> {
                        return true
                    }
                }
            }
        } else {
            val activeNetworkInfo = connectivityManager.activeNetworkInfo
            if (activeNetworkInfo != null && activeNetworkInfo.isConnected) {
                return true
            }
        }
        return false
    }
}