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
import androidx.lifecycle.ViewModelProvider
import com.example.walmart.R
import com.example.walmart.ui.viewmodel.BaseToolBarActivityViewModel
import kotlinx.android.synthetic.main.custom_action_bar_layout.*

abstract class BaseToolBarActivity : AppCompatActivity() {

    abstract fun isCartOn(): Boolean
    abstract fun isPastOrderOn(): Boolean
    abstract fun isBackOn(): Boolean
    abstract fun activityName(): String?


    override fun onCreateOptionsMenu(menu: Menu?): Boolean {

        supportActionBar?.let {
            it.displayOptions = ActionBar.DISPLAY_SHOW_CUSTOM
            it.setDisplayShowCustomEnabled(true)
            it.setCustomView(R.layout.custom_action_bar_layout)
        }

        val viewModel = ViewModelProvider(this).get(BaseToolBarActivityViewModel::class.java)

        supportActionBar?.customView?.apply {
            findViewById<TextView>(R.id.activity_name_custom_action_bar).text = activityName()

            findViewById<ImageView>(R.id.back_arrow_custom_action_bar).let { backArrow ->
                if (isBackOn()) {
                    backArrow.visibility = View.VISIBLE
                    backArrow.setOnClickListener {
                        onBackPressed()
                    }
                } else {
                    backArrow.visibility = View.GONE
                }
            }

            findViewById<ImageView>(R.id.past_order_custom_action_bar).let { pastOrder ->
                if (isPastOrderOn()) {
                    pastOrder.visibility = View.VISIBLE
                    pastOrder.setOnClickListener {
                        val intent =
                            Intent(this@BaseToolBarActivity, PastOrdersActivity::class.java)
                        startActivity(intent)
                    }
                } else {
                    pastOrder.visibility = View.GONE
                }
            }


            findViewById<ConstraintLayout>(R.id.cart_custom_action_bar).let { cart ->
                if (isCartOn()) {
                    cart.visibility = View.VISIBLE
                    cart.setOnClickListener {
                        val intent = Intent(this@BaseToolBarActivity, CartActivity::class.java)
                        startActivity(intent)
                    }
                } else {
                    cart.visibility = View.GONE
                }
            }
            viewModel.cartCount.observe(this@BaseToolBarActivity, Observer {
                updateCartCount(count = it)
            })

            return true

        } ?: return false

        return true
    }

    private fun updateCartCount(count: Int) {
        cart_icon_counter_custom_action_bar?.apply {
            if (count == 0) {
                visibility = View.GONE
            } else {
                visibility = View.VISIBLE
                text = count.toString()
            }
        }

    }
}