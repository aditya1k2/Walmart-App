package com.example.walmart.ui.adapter

import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.StaggeredGridLayoutManager
import com.example.walmart.R
import com.example.walmart.model.CategoryId
import com.example.walmart.ui.adapter.viewholders.ItemBaseViewHolderHome
import com.example.walmart.ui.adapter.viewholders.ItemViewHolderMainCategoryHome
import com.example.walmart.ui.adapter.viewholders.ItemViewHolderSubCategoryHome
import com.example.walmart.ui.screens.CategoryListActivity
import java.lang.IllegalArgumentException

class RvHomeAdapter :
    RecyclerView.Adapter<ItemBaseViewHolderHome>() {
    private val dat: ArrayList<CategoryId> = arrayListOf()


    fun updateData(dat: List<CategoryId>) {
        this.dat.clear()
        this.dat.addAll(dat)
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): ItemBaseViewHolderHome {

        return when (viewType) {
            R.layout.item_view_homerv -> {
                ItemViewHolderMainCategoryHome(
                    LayoutInflater
                        .from(parent.context)
                        .inflate(
                            R.layout.item_view_homerv,
                            parent,
                            false
                        )
                )
            }
            R.layout.item_view_subcategory_homerv -> {
                ItemViewHolderSubCategoryHome(
                    LayoutInflater
                        .from(parent.context)
                        .inflate(
                            R.layout.item_view_subcategory_homerv,
                            parent,
                            false
                        )
                )
            }
            else -> {
                throw IllegalArgumentException("Illegal Layout")
            }
        }
    }

    override fun getItemCount(): Int {
        return dat.size * 2
    }

    override fun onBindViewHolder(holder: ItemBaseViewHolderHome, position: Int) {
        holder.bind(dat[position / 2])
    }

    override fun getItemViewType(position: Int): Int {
        return if (position % 2 == 0)
            R.layout.item_view_homerv
        else
            R.layout.item_view_subcategory_homerv

    }


}