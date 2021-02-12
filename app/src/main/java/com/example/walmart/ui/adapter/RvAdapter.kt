package com.example.walmart.ui.adapter

import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.RatingBar
import android.widget.TextView
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import com.example.walmart.R
import com.example.walmart.model.Items
import com.example.walmart.ui.screens.ItemDetailActivity
import com.squareup.picasso.Picasso
import java.util.*

class RvAdapter : RecyclerView.Adapter<RvAdapter.ItemViewHolder>() {
    private val dat: ArrayList<Items> = arrayListOf()

    inner class ItemViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private val productThumbnail: ImageView = itemView.findViewById(R.id.productThumbnail)
        private val productName: TextView = itemView.findViewById(R.id.productName)
        private val brandName: TextView = itemView.findViewById(R.id.brandName)
        private val price: TextView = itemView.findViewById(R.id.price)
        private val ratingBar: RatingBar = itemView.findViewById(R.id.ratingBarCategoryList)
        fun bind(item: Items) {
            val a = "by ".plus("${item.brandName}")
            productName.text = item.name
            brandName.text = a
            price.text = item.salePrice.toString().plus(" $")
            Picasso.get().load(item.thumbnailImage)
                .placeholder(R.drawable.ic_launcher_foreground)
                .into(productThumbnail)
            if (item.customerRating == null)
                ratingBar.visibility = View.GONE
            else
                ratingBar.rating = item.customerRating

            itemView.setOnClickListener {
                Toast.makeText(itemView.context, "Clicked ", Toast.LENGTH_SHORT).show()
                val intent = Intent(itemView.context, ItemDetailActivity::class.java)
                intent.putExtra("short description", item.shortDescription)
                intent.putExtra("imageUrl", item.largeImage)
                intent.putExtra("productPrice", item.salePrice)
                intent.putExtra("productName", item.name)
                intent.putExtra("brandName", item.brandName)
                intent.putExtra("productRating", item.customerRating)
                itemView.context.startActivity(intent)
            }
        }
    }

    fun updateData(dat: List<Items>) {
//        this.dat.clear()
//        this.dat.addAll(dat)
        for (itr in dat) {
            this.dat.add(itr)
        }
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ItemViewHolder {
        return ItemViewHolder(
            LayoutInflater.from(parent.context).inflate(R.layout.item_view, parent, false)
        )
    }

    override fun getItemCount(): Int {
        return dat.size
    }

    override fun onBindViewHolder(holder: ItemViewHolder, position: Int) {
        holder.bind(dat[position])
    }
}