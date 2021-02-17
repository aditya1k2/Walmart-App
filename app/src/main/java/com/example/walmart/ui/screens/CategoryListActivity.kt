package com.example.walmart.ui.screens

import android.content.Context
import android.content.Intent
import android.net.ConnectivityManager
import android.net.NetworkCapabilities
import android.net.Uri
import android.os.Build
import android.os.Bundle
import android.util.Log
import android.view.Menu
import android.view.View
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.ActionBar
import androidx.appcompat.app.AppCompatActivity
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.walmart.R
import com.example.walmart.module.WalmartModule
import com.example.walmart.ui.adapter.RvCategoryListAdapter
import com.example.walmart.ui.viewmodel.CategoryListViewModel
import com.google.android.material.snackbar.Snackbar
import kotlinx.android.synthetic.main.activity_category_list.*
import kotlinx.android.synthetic.main.activity_item_detail.*


class CategoryListActivity : AppCompatActivity() {
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

//        supportActionBar?.setDisplayHomeAsUpEnabled(true)
//        supportActionBar?.title = intent.getStringExtra("categoryName")

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
//                            Snackbar.make(this@CategoryListActivity,"Retry",Snackbar.LENGTH_SHORT)
//                        Toast.makeText(this@CategoryListActivity, "Retry", Toast.LENGTH_SHORT)
//                            .show()
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
    override fun onCreateOptionsMenu(menu: Menu?): Boolean {

        supportActionBar?.displayOptions = ActionBar.DISPLAY_SHOW_CUSTOM
        supportActionBar?.setDisplayShowCustomEnabled(true)
        supportActionBar?.setCustomView(R.layout.custom_action_bar_layout)
        val view = supportActionBar?.customView
        val backArrow = view?.findViewById<ImageView>(R.id.back_arrow_custom_action_bar)
        val cartCount = view?.findViewById<TextView>(R.id.cart_icon_counter_custom_action_bar)
        val name = view?.findViewById<TextView>(R.id.activity_name_custom_action_bar)
        val cart = view?.findViewById<ConstraintLayout>(R.id.cart_custom_action_bar)
        val pastOrder = view?.findViewById<ImageView>(R.id.past_order_custom_action_bar)
        pastOrder?.setOnClickListener {
            val intent = Intent(this, PastOrdersActivity::class.java)
            startActivity(intent)
        }

        cart?.setOnClickListener {
            val intent = Intent(this, CartActivity::class.java)
            startActivity(intent)
        }

        name?.text = intent.getStringExtra("categoryName")
        cartCount?.text = WalmartModule.cartCount.toString()

        backArrow?.setOnClickListener {
            onBackPressed()
        }
        return true
    }





}