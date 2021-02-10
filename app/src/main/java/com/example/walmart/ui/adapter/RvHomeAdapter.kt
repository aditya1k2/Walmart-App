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
import com.example.walmart.ui.screens.CategoryListActivity

class RvHomeAdapter :
    RecyclerView.Adapter<RvHomeAdapter.BaseViewHolder>() {
    private val dat: ArrayList<CategoryId> = arrayListOf()

    abstract class BaseViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        abstract fun bind(item: CategoryId)
    }

    inner class ItemViewHolder(itemView: View) : BaseViewHolder(itemView) {
//        private val catId: TextView = itemView.findViewById(R.id.categoryID)
        private val catName: TextView = itemView.findViewById(R.id.categoryName)

        override fun bind(item: CategoryId) {
            //catId.text = item.id
            catName.text = item.name
            itemView.setOnClickListener {
                val intent = Intent(itemView.context, CategoryListActivity::class.java)
                intent.putExtra("categoryId", item.id)
                intent.putExtra("categoryName",item.name)
                itemView.context.startActivity(intent)
            }
        }
    }

    inner class ItemViewHolderSubCategory(itemView: View) : BaseViewHolder(itemView) {
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


    fun updateData(dat: List<CategoryId>) {
        this.dat.clear()
        this.dat.addAll(dat)
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BaseViewHolder {

        return when (viewType) {
            R.layout.item_view_homerv -> {
                ItemViewHolder(
                    LayoutInflater.from(parent.context)
                        .inflate(R.layout.item_view_homerv, parent, false)
                )
            }
            R.layout.item_view_subcategory_homerv -> {
                ItemViewHolderSubCategory(
                    LayoutInflater.from(parent.context)
                        .inflate(R.layout.item_view_subcategory_homerv, parent, false)
                )
            }
            else -> { // TODO: 29/1/21 throw exception here
                ItemViewHolder(
                    LayoutInflater.from(parent.context)
                        .inflate(R.layout.item_view_homerv, parent, false)
                )
            }
        }
    }

    override fun getItemCount(): Int {
        return dat.size * 2
    }

    override fun onBindViewHolder(holder: BaseViewHolder, position: Int) {

        holder.bind(dat[position / 2])
    }

    override fun getItemViewType(position: Int): Int {
        return if (position % 2 == 0)
            R.layout.item_view_homerv
        else
            R.layout.item_view_subcategory_homerv

    }


}