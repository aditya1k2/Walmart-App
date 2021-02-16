package com.example.walmart.ui.adapter.viewholders

import android.content.Intent
import android.view.View
import android.widget.TextView
import com.example.walmart.R
import com.example.walmart.model.CategoryId
import com.example.walmart.ui.screens.CategoryListActivity

class ItemViewHolderMainCategoryHome(itemView: View) : ItemBaseViewHolderHome(itemView) {


    private val catName: TextView = itemView.findViewById(R.id.categoryName)

    override fun bind(item: CategoryId) {
        catName.text = item.name

        itemView.setOnClickListener {

            val intent = Intent(itemView.context, CategoryListActivity::class.java)
            intent.putExtra("categoryId", item.id)
            intent.putExtra("categoryName", item.name)

            itemView.context.startActivity(intent)
        }
    }

}
