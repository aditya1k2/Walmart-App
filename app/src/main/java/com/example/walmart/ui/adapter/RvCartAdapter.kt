package com.example.walmart.ui.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.RatingBar
import android.widget.TextClock
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.walmart.R
import com.example.walmart.data.db.entities.ProductTable
import com.example.walmart.ui.adapter.viewholders.ItemViewHolderCart
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.activity_item_detail.*
import java.util.*

class RvCartAdapter(private val listener: IRvCartAdapter) :
    RecyclerView.Adapter<ItemViewHolderCart>() {
    private var dat: ArrayList<ProductTable> = arrayListOf()


    fun update(dat: List<ProductTable>) {
        this.dat.clear()
        this.dat.addAll(dat)
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): ItemViewHolderCart {
        return ItemViewHolderCart(
            LayoutInflater
                .from(parent.context)
                .inflate(
                    R.layout.item_view_cart,
                    parent,
                    false
                ),
            listener
        )
    }

    override fun getItemCount(): Int {
        return dat.size
    }

    override fun onBindViewHolder(holder: ItemViewHolderCart, position: Int) {
        holder.bind(dat[position])
    }

    interface IRvCartAdapter {
        fun onItemClicked(item: ProductTable)
    }
}