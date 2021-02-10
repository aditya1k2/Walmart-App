package com.example.walmart.ui.screens

import android.os.Bundle
import android.text.method.ScrollingMovementMethod
import android.view.Menu
import android.view.View
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import com.example.walmart.R
import com.example.walmart.data.db.entities.ProductTable
import com.example.walmart.ui.viewmodel.ItemDetailViewModel
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.activity_item_detail.*
import kotlinx.android.synthetic.main.cart_icon.*

class ItemDetailActivity : MenuActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_item_detail)

        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        val viewModel = ViewModelProvider(this).get(ItemDetailViewModel::class.java)

        val price = intent.getDoubleExtra("productPrice", 0.0)
        productPrice.text = "$price".plus(" $")
        val brandName = intent.getStringExtra("brandName")
        val rating = intent.getFloatExtra("productRating", 0f)
        val imageUrl = intent.getStringExtra("imageUrl")
        val productNameDetailActivity: String? = intent.getStringExtra("productName")


        Picasso.get().load(imageUrl).placeholder(R.drawable.ic_launcher_foreground)
            .into(productImage)

        productDescription.text = intent.getStringExtra("short description")
        productDescription.movementMethod = ScrollingMovementMethod()

        brandNameItemDetail.text = "by ".plus(brandName)
        if (rating == 0f)
            ratingBarItemDetail.visibility = View.GONE
        else
            ratingBarItemDetail.rating = rating




        productName.text = productNameDetailActivity
        addToCart.setOnClickListener {

            cart_icon_counter_toolbar.visibility = View.VISIBLE
            viewModel.insertProduct(
                ProductTable(
                    productNameDetailActivity,
                    price,
                    brandName,
                    rating,
                    imageUrl
                ), application
            )
        }


        buyNow.setOnClickListener {
            Toast.makeText(this, "Item Purchased", Toast.LENGTH_SHORT).show()
        }
    }


}