package com.example.walmart.ui.adapter.viewholders

import android.view.View
import android.widget.ImageView
import android.widget.RatingBar
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.walmart.R
import com.example.walmart.data.db.entities.PastOrder
import com.squareup.picasso.Picasso

class ItemViewHolderPastOrders(itemView: View) : RecyclerView.ViewHolder(itemView) {

    private val productThumbnail: ImageView = itemView.findViewById(R.id.productThumbnailPastOrders)

    private val productName: TextView = itemView.findViewById(R.id.productNamePastOrders)
    private val brandName: TextView = itemView.findViewById(R.id.brandNamePastOrder)
    private val price: TextView = itemView.findViewById(R.id.pricePastOrder)

    private val ratingBar: RatingBar = itemView.findViewById(R.id.ratingBarCategoryListPastOrder)

    fun bind(item: PastOrder) {

        val a = "by ".plus("${item.brand}")
        productName.text = item.productName
        brandName.text = a
        price.text = item.productPrice.toString().plus(" $")

        Picasso.get().load(item.imageUrl)
            .placeholder(R.drawable.ic_launcher_foreground)
            .into(productThumbnail)

        if (item.rating == null)
            ratingBar.visibility = View.GONE
        else {

            ratingBar.visibility = View.VISIBLE
            ratingBar.rating = item.rating
        }
    }
}
