package com.example.walmart.ui.screens

import android.content.Intent
import android.view.Menu
import android.view.MenuItem
import android.widget.ImageView
import android.widget.Toast
import androidx.appcompat.app.ActionBar
import androidx.appcompat.app.AppCompatActivity
import com.example.walmart.R

open class MenuActivity : AppCompatActivity() {

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {

        supportActionBar?.displayOptions = ActionBar.DISPLAY_SHOW_CUSTOM
        supportActionBar?.setDisplayShowCustomEnabled(true)
        supportActionBar?.setCustomView(R.layout.custom_action_bar_layout)
        val view = supportActionBar?.customView

        val backArrow = view?.findViewById<ImageView>(R.id.back_arrow_custom_action_bar)
        val pastOrder = view?.findViewById<ImageView>(R.id.past_order_custom_action_bar)
//        val cart = view?.findViewById<ImageView>(R.id.cart_custom_action_bar)

        backArrow?.setOnClickListener {
            onBackPressed()
        }

//        menuInflater.inflate(R.menu.main_menu, menu)
//        val item: MenuItem? = menu?.findItem(R.id.cart)
//        item?.actionView?.findViewById<ImageView>(R.id.cart_icon_toolbar)?.setOnClickListener {
//            onOptionsItemSelected(item)
//        }
        return true
    }

//    override fun onOptionsItemSelected(item: MenuItem): Boolean {
//        when (item.itemId) {
//            R.id.cart -> {
//                Toast.makeText(this, "Cart clicked", Toast.LENGTH_SHORT).show()
//                val intent = Intent(this, CartActivity::class.java)
//                startActivity(intent)
//            }
//            R.id.order -> {
//                val intent = Intent(this, PastOrdersActivity::class.java)
//                startActivity(intent)
//            }
//            else -> onBackPressed()
//        }
//
//        return super.onOptionsItemSelected(item)
//    }


}