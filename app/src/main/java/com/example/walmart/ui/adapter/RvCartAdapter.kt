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
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.activity_item_detail.*
import java.util.*

class RvCartAdapter(private val listener: IRvCartAdapter) :
    RecyclerView.Adapter<RvCartAdapter.ItemViewHolder>() {
    private var dat: ArrayList<ProductTable> = arrayListOf()


    inner class ItemViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private val productThumbnail: ImageView = itemView.findViewById(R.id.productThumbnailCart)
        private val productNameCart: TextView = itemView.findViewById(R.id.productNameCart)
        private val brandNameCart: TextView = itemView.findViewById(R.id.brandNameCart)
        private val ratingBarCart: RatingBar = itemView.findViewById(R.id.ratingBarCart)
        private val priceCart: TextView = itemView.findViewById(R.id.priceCart)
        private val deleteProductFromCart: ImageView =
            itemView.findViewById(R.id.deleteProductFromCart)

        fun bind(item: ProductTable) {
            priceCart.text = item.productPrice.toString()
            Picasso.get().load(item.imageUrl).placeholder(R.drawable.ic_launcher_foreground)
                .into(productThumbnail)
            productNameCart.text = item.productName
            brandNameCart.text = item.brand
            if (item.rating == 0f)
                ratingBarCart.visibility = View.GONE
            else
                ratingBarCart.rating = item.rating
            deleteProductFromCart.setOnClickListener {
                listener.onItemClicked(item)
            }

        }

    }

    fun update(dat: List<ProductTable>) {
        this.dat.clear()
        this.dat.addAll(dat)
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ItemViewHolder {
        return ItemViewHolder(
            LayoutInflater.from(parent.context).inflate(R.layout.item_view_cart, parent, false)
        )
    }

    override fun getItemCount(): Int {
        return dat.size
    }

    override fun onBindViewHolder(holder: ItemViewHolder, position: Int) {
        holder.bind(dat[position])
    }

    interface IRvCartAdapter {
        fun onItemClicked(item: ProductTable)
    }
}