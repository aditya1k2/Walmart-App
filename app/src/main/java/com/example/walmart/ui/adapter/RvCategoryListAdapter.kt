package com.example.walmart.ui.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.walmart.R
import com.example.walmart.model.Items
import com.example.walmart.ui.adapter.viewholders.ItemViewHolderCategoryList
import java.util.*

class RvCategoryListAdapter : RecyclerView.Adapter<ItemViewHolderCategoryList>() {
    private val dat: ArrayList<Items> = arrayListOf()

    fun updateData(dat: List<Items>) {
        for (itr in dat) {
            this.dat.add(itr)
        }
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): ItemViewHolderCategoryList {
        return ItemViewHolderCategoryList(
            LayoutInflater
                .from(parent.context)
                .inflate(
                    R.layout.item_view,
                    parent,
                    false
                )
        )
    }

    override fun getItemCount(): Int {
        return dat.size
    }

    override fun onBindViewHolder(holder: ItemViewHolderCategoryList, position: Int) {
        holder.bind(dat[position])
    }
}