package com.example.walmart.ui.screens

import android.os.Bundle
import android.view.View
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.walmart.R
import com.example.walmart.ui.adapter.RvHomeAdapter
import com.example.walmart.ui.viewmodel.HomeViewModel
import kotlinx.android.synthetic.main.activity_home.*

class HomeActivity : MenuActivity() {
    private var flag: Boolean = false

    private val homeViewModel by lazy {
        ViewModelProvider(this).get(HomeViewModel::class.java)
    }

    private val adapter = RvHomeAdapter()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_home)

        isCartOn = true
        isPastOrderOn = true
        isBackOn = false
        activityName = "Walmart"

        homeRecyclerView.layoutManager = LinearLayoutManager(this)
        homeRecyclerView.adapter = adapter

        homeViewModel.getCategoryList()
        homeViewModel.categoryListData.observe(this, Observer {
            shimmer_view_container_home.stopShimmerAnimation()
            shimmer_view_container_home.visibility = View.GONE

            flag = true
            adapter.updateData(it)
        })
    }

    override fun onResume() {
        if (!flag)
            shimmer_view_container_home.startShimmerAnimation()
        super.onResume()
    }

    override fun onPause() {
        shimmer_view_container_home.stopShimmerAnimation()
        super.onPause()
    }
}