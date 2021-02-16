package com.example.walmart.ui.adapter.viewholders

import android.view.View
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.StaggeredGridLayoutManager
import com.example.walmart.R
import com.example.walmart.model.CategoryId
import com.example.walmart.ui.adapter.RvSubCategoryAdapter

class ItemViewHolderSubCategoryHome(itemView: View) : ItemBaseViewHolderHome(itemView) {
    private val subCategoryRecyclerView: RecyclerView =
        itemView.findViewById(R.id.subCategoryRecyclerView)

    override fun bind(item: CategoryId) {
        val adapter = RvSubCategoryAdapter()
        subCategoryRecyclerView.layoutManager =
            StaggeredGridLayoutManager(3, LinearLayoutManager.HORIZONTAL)
        subCategoryRecyclerView.adapter = adapter
        adapter.updateData(item.children)
    }
}