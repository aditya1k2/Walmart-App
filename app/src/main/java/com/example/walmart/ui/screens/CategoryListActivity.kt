package com.example.walmart.ui.screens

import android.net.Uri
import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.walmart.R
import com.example.walmart.ui.adapter.RvAdapter
import com.example.walmart.ui.viewmodel.WalmartViewModel
import kotlinx.android.synthetic.main.activity_category_list.*

class CategoryListActivity : MenuActivity() {

    private var flag: Boolean = false
    private var isLoading: Boolean = true

    //    private var viewThreshold: Int = 20
    private var scrollOut: Int = 0
    private var visibleItemCount: Int = 0
    private var totalItemCount: Int = 0
    private var previousTotal: Int = 0
    private val manager = LinearLayoutManager(this)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_category_list)

        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        supportActionBar?.title = intent.getStringExtra("categoryName")

        val categoryId: String? = intent.getStringExtra("categoryId")
        val viewModel = ViewModelProvider(this).get(WalmartViewModel::class.java)

        val adapter = RvAdapter()
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

//        viewModel.categoryListDataPagination.observe(this, Observer {
//            Log.d("Pagination", "Observe Pagination$it")
//            adapter.updateData(it.items)
//            isLoading = true
//
//        })


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
                    Log.d("Pagination", "Scrolled")
                    viewModel.categoryListData.value?.nextPage
                    if (isLoading && (visibleItemCount + scrollOut == totalItemCount)) {
                        progressBar.visibility = View.VISIBLE
                        isLoading = false
                        viewModel.getCategoryListDataPagination(
                            categoryId,
                            lastDoc,
                            remainingHits
                        )
                    }
                }
            }
        })

    }

    override fun onResume() {
        if (!flag)
            shimmer_view_container.startShimmerAnimation()
        super.onResume()
    }

    override fun onPause() {
        shimmer_view_container.stopShimmerAnimation()
        super.onPause()
    }
}