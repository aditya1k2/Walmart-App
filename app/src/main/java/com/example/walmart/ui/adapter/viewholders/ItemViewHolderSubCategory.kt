package com.example.walmart.ui.adapter.viewholders

import android.content.Intent
import android.view.View
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.walmart.R
import com.example.walmart.model.CategoryId
import com.example.walmart.ui.screens.CategoryListActivity

class ItemViewHolderSubCategory(itemView: View) : RecyclerView.ViewHolder(itemView) {

    private val textView: TextView = itemView.findViewById(R.id.item_view_subcategory)

    fun bind(item: CategoryId) {
        textView.text = item.name
        textView.setOnClickListener {
            val intent = Intent(itemView.context, CategoryListActivity::class.java)
            intent.putExtra("categoryId", item.id)
            intent.putExtra("categoryName", item.name)
            itemView.context.startActivity(intent)
        }

    }
}