package com.example.walmart.ui.screens

import android.content.Intent
import android.os.Bundle
import android.text.method.ScrollingMovementMethod
import android.view.Menu
import android.view.View
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.ActionBar
import androidx.appcompat.app.AppCompatActivity
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.example.walmart.R
import com.example.walmart.data.db.entities.PastOrder
import com.example.walmart.data.db.entities.ProductTable
import com.example.walmart.module.WalmartModule
import com.example.walmart.ui.viewmodel.ItemDetailViewModel
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.activity_item_detail.*
import kotlinx.android.synthetic.main.cart_icon.*

class ItemDetailActivity : AppCompatActivity() {
    private val viewModel by lazy {
        ViewModelProvider(this).get(ItemDetailViewModel::class.java)
    }


    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_item_detail)

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
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {

        supportActionBar?.displayOptions = ActionBar.DISPLAY_SHOW_CUSTOM
        supportActionBar?.setDisplayShowCustomEnabled(true)
        supportActionBar?.setCustomView(R.layout.custom_action_bar_layout)
        val view = supportActionBar?.customView
        val backArrow = view?.findViewById<ImageView>(R.id.back_arrow_custom_action_bar)
        val cartCount = view?.findViewById<TextView>(R.id.cart_icon_counter_custom_action_bar)
        val name = view?.findViewById<TextView>(R.id.activity_name_custom_action_bar)
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

        val price = intent.getDoubleExtra("productPrice", 0.0)
        val brandName = intent.getStringExtra("brandName")
        val rating = intent.getFloatExtra("productRating", 0f)
        val imageUrl = intent.getStringExtra("imageUrl")
        val productNameDetailActivity: String? = intent.getStringExtra("productName")


        name?.text = "Product Detail"

        var c = WalmartModule.cartCount
        cartCount?.text = c.toString()

        WalmartModule.cartCount.observe(this, Observer {
            if (it == 0) {
                cartCount?.visibility = View.GONE
            } else {
                cartCount?.visibility = View.VISIBLE
                cartCount?.text = WalmartModule.cartCount.value.toString()
            }
//            cartCount?.text = WalmartModule.cartCount.value.toString()
        })

        addToCart.setOnClickListener {

//            c = WalmartModule.cartCount
//            cartCount?.text = c.toString()
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

        backArrow?.setOnClickListener {
            onBackPressed()
        }
        return true
    }

}