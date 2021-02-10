package com.example.walmart.ui.screens

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.Menu
import android.view.MenuItem
import android.view.View
import android.widget.TextView
import androidx.lifecycle.LiveData
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.walmart.R
import com.example.walmart.data.db.entities.ProductTable
import com.example.walmart.ui.adapter.RvCartAdapter
import com.example.walmart.ui.viewmodel.CartViewModel
import kotlinx.android.synthetic.main.activity_cart.*
import kotlinx.android.synthetic.main.cart_icon.*
import kotlin.math.roundToInt

class CartActivity : AppCompatActivity(), RvCartAdapter.IRvCartAdapter {
    private lateinit var viewModel: CartViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_cart)

        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        supportActionBar?.title = "Cart"

        viewModel = ViewModelProvider(this).get(CartViewModel::class.java)

        val adapter = RvCartAdapter(this)
        cartRecyclerView.layoutManager = LinearLayoutManager(this)
        cartRecyclerView.adapter = adapter


        val cartData: LiveData<List<ProductTable>> = viewModel.getCartDetails(application)
        cartData.observe(this, Observer {
            adapter.update(it)
            if (cartData.value?.size == 0) {
                cartRelativeLayout.visibility = View.GONE
            } else {
                var cartSum: Double = 0.0
                for (item in it) {
                    cartSum += item.productPrice
                }
                cartSum = (cartSum * 100.0).roundToInt() / 100.0
                cartRelativeLayout.visibility = View.VISIBLE
                cartTotalValue.text = cartSum.toString().plus(" $")

            }
            Log.d("Cart Count", cartData.value?.size.toString())
        })

    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        onBackPressed()
        return super.onOptionsItemSelected(item)
    }

    override fun onItemClicked(item: ProductTable) {
        viewModel.deleteProduct(item, application)
    }
}