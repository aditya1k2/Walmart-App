package com.example.walmart.ui.screens

import android.content.Intent
import android.os.Bundle
import android.util.Log
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
import com.example.walmart.data.db.entities.ProductTable
import com.example.walmart.module.WalmartModule
import com.example.walmart.ui.adapter.RvCartAdapter
import com.example.walmart.ui.viewmodel.CartViewModel
import kotlinx.android.synthetic.main.activity_cart.*
import kotlinx.android.synthetic.main.activity_item_detail.*
import kotlin.math.roundToInt

class CartActivity : AppCompatActivity(), RvCartAdapter.IRvCartAdapter {
    private val viewModel by lazy {
        ViewModelProvider(this).get(CartViewModel::class.java)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_cart)

//        supportActionBar?.setDisplayHomeAsUpEnabled(true)
//        supportActionBar?.title = "Cart"


        val adapter = RvCartAdapter(this)
        cartRecyclerView.layoutManager = LinearLayoutManager(this)
        cartRecyclerView.adapter = adapter


        val cartData: LiveData<List<ProductTable>> = viewModel.getCartDetails()
        cartData.observe(this, Observer {
            adapter.update(it)


            if (cartData.value?.size == 0) {
                cartRelativeLayout.visibility = View.GONE
                cartEmptyTv.visibility = View.VISIBLE

            } else {
                var cartSum = 0.0
                for (item in it) {
                    cartSum += item.productPrice
                }

                cartSum = (cartSum * 100.0).roundToInt() / 100.0
                cartRelativeLayout.visibility = View.VISIBLE
                cartEmptyTv.visibility = View.GONE

                cartTotalValue.text = cartSum.toString().plus(" $")

            }

            Log.d("Cart Count", cartData.value?.size.toString())
        })

        payNowButtonCart.setOnClickListener {
            for (itr in cartData.value!!) {
                viewModel.insertOrder(
                    PastOrder(
                        itr.productName,
                        itr.productPrice,
                        itr.brand,
                        itr.rating,
                        itr.imageUrl
                    )
                )
                viewModel.deleteProduct(itr)

                WalmartModule.notification(this, "2")

                val intent = Intent(this, PastOrdersActivity::class.java)
                startActivity(intent)
            }
        }
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        onBackPressed()
        return super.onOptionsItemSelected(item)
    }

    override fun onItemClicked(item: ProductTable) {
        viewModel.deleteProduct(item)
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {

        supportActionBar?.displayOptions = ActionBar.DISPLAY_SHOW_CUSTOM
        supportActionBar?.setDisplayShowCustomEnabled(true)
        supportActionBar?.setCustomView(R.layout.custom_action_bar_layout)
        val view = supportActionBar?.customView
        val backArrow = view?.findViewById<ImageView>(R.id.back_arrow_custom_action_bar)
        val name = view?.findViewById<TextView>(R.id.activity_name_custom_action_bar)
        val cart = view?.findViewById<ConstraintLayout>(R.id.cart_custom_action_bar)
        val pastOrder = view?.findViewById<ImageView>(R.id.past_order_custom_action_bar)

        pastOrder?.setOnClickListener {
            val intent = Intent(this, PastOrdersActivity::class.java)
            startActivity(intent)
        }

        cart?.visibility = View.GONE
        name?.text = "Cart"

        backArrow?.setOnClickListener {
            onBackPressed()
        }
        return true
    }
}