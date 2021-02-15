package com.example.walmart.ui.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.walmart.R
import com.example.walmart.model.CategoryId
import com.example.walmart.ui.adapter.viewholders.ItemViewHolderSubCategory
import java.util.*

class RvSubCategoryAdapter : RecyclerView.Adapter<ItemViewHolderSubCategory>() {
    private val dat: ArrayList<CategoryId> = arrayListOf()


    fun updateData(dat: List<CategoryId>) {
        this.dat.clear()
        this.dat.addAll(dat)
        notifyDataSetChanged()
    }


    override fun onCreateViewHolder(
        parent: ViewGroup, viewType: Int
    ): ItemViewHolderSubCategory {
        return ItemViewHolderSubCategory(
            LayoutInflater
                .from(parent.context)
                .inflate(
                    R.layout.item_view_subcategory,
                    parent,
                    false
                )
        )
    }


    override fun getItemCount(): Int {
        return dat.size
    }

    override fun onBindViewHolder(holder: ItemViewHolderSubCategory, position: Int) {
        holder.bind(dat[position])
    }
}