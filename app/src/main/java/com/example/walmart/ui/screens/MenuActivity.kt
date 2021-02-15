package com.example.walmart.ui.screens

import android.content.Intent
import android.view.Menu
import android.view.MenuItem
import android.widget.ImageView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.walmart.R

open class MenuActivity : AppCompatActivity() {

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.main_menu, menu)
        val item: MenuItem? = menu?.findItem(R.id.cart)
        item?.actionView?.findViewById<ImageView>(R.id.cart_icon_toolbar)?.setOnClickListener {
            onOptionsItemSelected(item)
        }
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.cart -> {
                Toast.makeText(this, "Cart clicked", Toast.LENGTH_SHORT).show()
                val intent = Intent(this, CartActivity::class.java)
                startActivity(intent)
            }
            R.id.order -> {
                val intent = Intent(this, PastOrdersActivity::class.java)
                startActivity(intent)
            }
            else -> onBackPressed()
        }

        return super.onOptionsItemSelected(item)
    }


}