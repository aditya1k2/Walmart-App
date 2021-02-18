package com.example.walmart.ui.screens

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.View
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
import kotlin.math.roundToInt

class CartActivity : MenuActivity(), RvCartAdapter.IRvCartAdapter {
    private val viewModel by lazy {
        ViewModelProvider(this).get(CartViewModel::class.java)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_cart)

        isBackOn = true
        isCartOn = false
        isPastOrderOn = true
        activityName = "Cart"


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
                finish()
                startActivity(intent)
            }
        }
    }

    override fun onItemClicked(item: ProductTable) {
        viewModel.deleteProduct(item)
    }

}