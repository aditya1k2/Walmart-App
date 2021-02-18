package com.example.walmart.ui.screens

import android.os.Bundle
import android.view.View
import androidx.lifecycle.LiveData
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.walmart.R
import com.example.walmart.data.db.entities.PastOrder
import com.example.walmart.ui.adapter.RvPastOrdersAdapter
import com.example.walmart.ui.viewmodel.PastOrderViewModel
import kotlinx.android.synthetic.main.activity_past_orders.*

class PastOrdersActivity : BaseToolBarActivity() {
    private val viewModel by lazy {
        ViewModelProvider(this).get(PastOrderViewModel::class.java)
    }

    override fun isCartOn(): Boolean {
        return true
    }

    override fun isPastOrderOn(): Boolean {
        return false
    }

    override fun isBackOn(): Boolean {
        return true
    }

    override fun activityName(): String? {
        return "Past Orders"
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_past_orders)

        pastOrderRecycleView.layoutManager = LinearLayoutManager(this)
        val adapter = RvPastOrdersAdapter()
        pastOrderRecycleView.adapter = adapter

        val pastOrder: LiveData<List<PastOrder>> = viewModel.getPastOrders()
        pastOrder.observe(this, Observer {
            if (pastOrder.value?.size == 0) {
                noPastOrderTv.visibility = View.VISIBLE
            } else {
                noPastOrderTv.visibility = View.GONE
                adapter.update(it)
            }
        })

    }
}