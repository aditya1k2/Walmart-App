package com.example.walmart.ui.screens

import android.content.Intent
import android.os.Bundle
import android.view.Menu
import android.view.View
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.app.ActionBar
import androidx.appcompat.app.AppCompatActivity
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.walmart.R
import com.example.walmart.module.WalmartModule
import com.example.walmart.ui.adapter.RvHomeAdapter
import com.example.walmart.ui.viewmodel.HomeViewModel
import kotlinx.android.synthetic.main.activity_home.*
import kotlinx.android.synthetic.main.custom_action_bar_layout.*

class HomeActivity : AppCompatActivity() {
    private var flag: Boolean = false

    private val homeViewModel by lazy {
        ViewModelProvider(this).get(HomeViewModel::class.java)
    }

    private val adapter = RvHomeAdapter()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_home)

//        back_arrow_custom_action_bar.visibility = View.GONE

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
//        back_arrow_custom_action_bar.visibility = View.GONE

        super.onResume()
    }

    override fun onPause() {
        shimmer_view_container_home.stopShimmerAnimation()
//        back_arrow_custom_action_bar.visibility = View.VISIBLE

        super.onPause()
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {

        supportActionBar?.displayOptions = ActionBar.DISPLAY_SHOW_CUSTOM
        supportActionBar?.setDisplayShowCustomEnabled(true)
        supportActionBar?.setCustomView(R.layout.custom_action_bar_layout)
        val view = supportActionBar?.customView

        val backArrow = view?.findViewById<ImageView>(R.id.back_arrow_custom_action_bar)
        val name = view?.findViewById<TextView>(R.id.activity_name_custom_action_bar)
        val cartCount = view?.findViewById<TextView>(R.id.cart_icon_counter_custom_action_bar)
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



        name?.text = "Walmart"
        backArrow?.visibility = View.GONE
        WalmartModule.cartCount.observe(this, Observer {
            if (it == 0) {
                cartCount?.visibility = View.GONE
            } else {
                cartCount?.visibility = View.VISIBLE
                cartCount?.text = WalmartModule.cartCount.value.toString()
            }
        })
//        cartCount?.text = WalmartModule.cartCount.toString()
        return true
    }


}