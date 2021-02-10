package com.example.walmart.ui.screens

import android.os.Bundle
import android.view.View
import android.widget.Toolbar
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.walmart.R
import com.example.walmart.ui.adapter.RvHomeAdapter
import com.example.walmart.ui.viewmodel.HomeViewModel
import kotlinx.android.synthetic.main.activity_home.*

class HomeActivity : MenuActivity() {
    private var flag: Boolean = false
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_home)

        val homeViewModel = ViewModelProvider(this).get(HomeViewModel::class.java)

        val adapter = RvHomeAdapter()
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