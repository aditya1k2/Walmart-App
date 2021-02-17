package com.example.walmart.ui.screens

import android.content.Intent
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.view.View
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.app.ActionBar
import androidx.appcompat.app.AppCompatActivity
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.lifecycle.LiveData
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.walmart.R
import com.example.walmart.data.db.entities.PastOrder
import com.example.walmart.module.WalmartModule
import com.example.walmart.ui.adapter.RvPastOrdersAdapter
import com.example.walmart.ui.viewmodel.PastOrderViewModel
import kotlinx.android.synthetic.main.activity_item_detail.*
import kotlinx.android.synthetic.main.activity_past_orders.*

class PastOrdersActivity : AppCompatActivity() {
    private val viewModel by lazy {
        ViewModelProvider(this).get(PastOrderViewModel::class.java)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_past_orders)
//        supportActionBar?.setDisplayHomeAsUpEnabled(true)
//        supportActionBar?.title = "Item Ordered"


        pastOrderRecycleView.layoutManager = LinearLayoutManager(this)
        val adapter = RvPastOrdersAdapter()
        pastOrderRecycleView.adapter = adapter

        val pastOrder: LiveData<List<PastOrder>> = viewModel.getPastOrders()
        pastOrder.observe(this, Observer {
            if (pastOrder.value?.size == 0) {
                noPastOrderTv.visibility = View.VISIBLE
            } else {
                noPastOrderTv.visibility = View.GONE
                adapter.update(it)
            }
        })
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        onBackPressed()
        return super.onOptionsItemSelected(item)
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {

        supportActionBar?.displayOptions = ActionBar.DISPLAY_SHOW_CUSTOM
        supportActionBar?.setDisplayShowCustomEnabled(true)
        supportActionBar?.setCustomView(R.layout.custom_action_bar_layout)
        val view = supportActionBar?.customView

        val backArrow = view?.findViewById<ImageView>(R.id.back_arrow_custom_action_bar)
        val pastOrder = view?.findViewById<ImageView>(R.id.past_order_custom_action_bar)
        val cartCount = view?.findViewById<TextView>(R.id.cart_icon_counter_custom_action_bar)
        val name = view?.findViewById<TextView>(R.id.activity_name_custom_action_bar)
        val cart = view?.findViewById<ConstraintLayout>(R.id.cart_custom_action_bar)

        cart?.setOnClickListener {
            val intent = Intent(this, CartActivity::class.java)
            startActivity(intent)
        }

        pastOrder?.visibility = View.GONE

        WalmartModule.cartCount.observe(this, Observer {
            if (it == 0) {
                cartCount?.visibility = View.GONE
            } else {
                cartCount?.visibility = View.VISIBLE
                cartCount?.text = WalmartModule.cartCount.value.toString()
            }
//            cartCount?.text = WalmartModule.cartCount.value.toString()
        })
//        cartCount?.text = WalmartModule.cartCount.toString()
        name?.text = "Past Orders"

        backArrow?.setOnClickListener {
            onBackPressed()
        }

        return true
    }
}