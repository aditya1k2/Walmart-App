package com.example.walmart.ui.screens

import android.content.Intent
import android.os.Bundle
import android.text.method.ScrollingMovementMethod
import android.view.View
import androidx.lifecycle.ViewModelProvider
import com.example.walmart.R
import com.example.walmart.data.db.entities.PastOrder
import com.example.walmart.data.db.entities.ProductTable
import com.example.walmart.module.WalmartModule
import com.example.walmart.ui.viewmodel.ItemDetailViewModel
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.activity_item_detail.*

class ItemDetailActivity : MenuActivity() {
    private val viewModel by lazy {
        ViewModelProvider(this).get(ItemDetailViewModel::class.java)
    }


    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_item_detail)

        isCartOn = true
        isPastOrderOn = true
        isBackOn = true
        activityName = "Product Detail"

        val price = intent.getDoubleExtra("productPrice", 0.0)
        val brandName = intent.getStringExtra("brandName")
        val rating = intent.getFloatExtra("productRating", 0f)
        val imageUrl = intent.getStringExtra("imageUrl")
        val productNameDetailActivity: String? = intent.getStringExtra("productName")

        Picasso.get().load(imageUrl).placeholder(R.drawable.ic_launcher_foreground)
            .into(productImage)

        productPrice.text = "$price".plus(" $")
        productDescription.text = intent.getStringExtra("short description")
        productDescription.movementMethod = ScrollingMovementMethod()

        brandNameItemDetail.text = "by ".plus(brandName)
        if (rating == 0f)
            ratingBarItemDetail.visibility = View.GONE
        else {
            ratingBarItemDetail.visibility = View.VISIBLE
            ratingBarItemDetail.rating = rating
        }


        productName.text = productNameDetailActivity

        buyNow.setOnClickListener {
            viewModel.insertOrder(
                PastOrder(
                    productNameDetailActivity,
                    price,
                    brandName,
                    rating,
                    imageUrl
                )
            )
            WalmartModule.notification(this, "1")
            val intent = Intent(this, PastOrdersActivity::class.java)
            intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP)
            startActivity(intent)

        }

        addToCart.setOnClickListener {

            viewModel.insertProduct(
                ProductTable(
                    productNameDetailActivity,
                    price,
                    brandName,
                    rating,
                    imageUrl
                )
            )
        }


    }
}