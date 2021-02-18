package com.example.walmart.ui.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.walmart.R
import com.example.walmart.data.db.entities.PastOrder
import com.example.walmart.ui.adapter.viewholders.ItemViewHolderPastOrders
import java.util.*

class RvPastOrdersAdapter : RecyclerView.Adapter<ItemViewHolderPastOrders>() {

    private var dat: ArrayList<PastOrder> = arrayListOf()

    fun update(dat: List<PastOrder>) {
        this.dat.clear()
        this.dat.addAll(dat)
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): ItemViewHolderPastOrders {
        return ItemViewHolderPastOrders(
            LayoutInflater
                .from(parent.context)
                .inflate(
                    R.layout.item_view_past_orders,
                    parent,
                    false
                )
        )
    }

    override fun getItemCount(): Int {
        return dat.size
    }

    override fun onBindViewHolder(holder: ItemViewHolderPastOrders, position: Int) {
        holder.bind(dat[position])
    }

}