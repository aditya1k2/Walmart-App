package com.example.walmart.ui.adapter.viewholders

import android.view.View
import android.widget.ImageView
import android.widget.RatingBar
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.walmart.R
import com.example.walmart.data.db.entities.ProductTable
import com.example.walmart.ui.adapter.RvCartAdapter
import com.squareup.picasso.Picasso

class ItemViewHolderCart(
    itemView: View,
    private val listener: RvCartAdapter.IRvCartAdapter
) : RecyclerView.ViewHolder(itemView) {

    private val productThumbnail: ImageView = itemView.findViewById(R.id.productThumbnailCart)
    private val deleteProductFromCart: ImageView = itemView.findViewById(R.id.deleteProductFromCart)

    private val productNameCart: TextView = itemView.findViewById(R.id.productNameCart)
    private val brandNameCart: TextView = itemView.findViewById(R.id.brandNameCart)
    private val priceCart: TextView = itemView.findViewById(R.id.priceCart)

    private val ratingBarCart: RatingBar = itemView.findViewById(R.id.ratingBarCart)

    fun bind(item: ProductTable) {

        priceCart.text = item.productPrice.toString()
        productNameCart.text = item.productName
        brandNameCart.text = item.brand

        Picasso
            .get()
            .load(item.imageUrl)
            .placeholder(R.drawable.ic_launcher_foreground)
            .into(productThumbnail)

        if (item.rating == 0f)
            ratingBarCart.visibility = View.GONE
        else {
            ratingBarCart.visibility = View.VISIBLE
            ratingBarCart.rating = item.rating
        }

        deleteProductFromCart.setOnClickListener {
            listener.onItemClicked(item)
        }
    }
}