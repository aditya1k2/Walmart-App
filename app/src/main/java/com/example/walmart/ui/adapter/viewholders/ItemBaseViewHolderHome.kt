package com.example.walmart.ui.adapter.viewholders

import android.view.View
import androidx.recyclerview.widget.RecyclerView
import com.example.walmart.model.CategoryId

abstract class ItemBaseViewHolderHome(itemView: View) : RecyclerView.ViewHolder(itemView) {
    abstract fun bind(item: CategoryId)
}