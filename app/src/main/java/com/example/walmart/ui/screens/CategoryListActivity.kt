package com.example.walmart.ui.screens

import android.os.Bundle
import android.view.View
import android.widget.Toolbar
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.walmart.R
import com.example.walmart.ui.viewmodel.WalmartViewModel
import com.example.walmart.ui.adapter.RvAdapter
import kotlinx.android.synthetic.main.activity_category_list.*
import kotlinx.android.synthetic.main.activity_home.*

class CategoryListActivity : MenuActivity() {

    private var flag: Boolean = false
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_category_list)

        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        supportActionBar?.title = intent.getStringExtra("categoryName")

        val categoryId: String? = intent.getStringExtra("categoryId")
        val viewModel = ViewModelProvider(this).get(WalmartViewModel::class.java)

        val adapter = RvAdapter()
        recyclerView.layoutManager = LinearLayoutManager(this)
        recyclerView.adapter = adapter

        viewModel.getCategoryListData(categoryId)
        viewModel.categoryListData.observe(this, Observer {
            shimmer_view_container.stopShimmerAnimation()
            shimmer_view_container.visibility = View.GONE
            flag = true
            adapter.updateData(it)
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