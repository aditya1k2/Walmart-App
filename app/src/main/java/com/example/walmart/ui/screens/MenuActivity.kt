package com.example.walmart.ui.screens

import android.content.Intent
import android.view.Menu
import android.view.View
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.app.ActionBar
import androidx.appcompat.app.AppCompatActivity
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.lifecycle.Observer
import com.example.walmart.R
import com.example.walmart.module.WalmartModule

abstract class MenuActivity : AppCompatActivity() {

    var isBackOn: Boolean = false
    var activityName: String? = ""
    var isPastOrderOn: Boolean = false
    var isCartOn: Boolean = false

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

        name?.text = activityName
        if (isBackOn) {
            backArrow?.visibility = View.VISIBLE
            backArrow?.setOnClickListener {
                onBackPressed()
            }
        } else {
            backArrow?.visibility = View.GONE
        }

        if (isPastOrderOn) {
            pastOrder?.visibility = View.VISIBLE
            pastOrder?.setOnClickListener {
                val intent = Intent(this, PastOrdersActivity::class.java)
                startActivity(intent)
            }
        } else {
            pastOrder?.visibility = View.GONE
        }

        if (isCartOn) {
            cart?.visibility = View.VISIBLE
            cart?.setOnClickListener {
                val intent = Intent(this, CartActivity::class.java)
                startActivity(intent)
            }
        } else {
            cart?.visibility = View.GONE
        }

        WalmartModule.cartCount.observe(this, Observer {
            if (it == 0) {
                cartCount?.visibility = View.GONE
            } else {
                cartCount?.visibility = View.VISIBLE
                cartCount?.text = WalmartModule.cartCount.value.toString()
            }
        })
        return true
    }
}