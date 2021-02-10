package com.example.walmart.ui.adapter

import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.walmart.R
import com.example.walmart.model.CategoryId
import com.example.walmart.ui.screens.CategoryListActivity
import java.util.*

class RvSubCategoryAdapter :
    RecyclerView.Adapter<RvSubCategoryAdapter.SubCategoryViewHolder>() {
    private val dat: ArrayList<CategoryId> = arrayListOf()

    inner class SubCategoryViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
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

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SubCategoryViewHolder {
        return SubCategoryViewHolder(
            LayoutInflater.from(parent.context)
                .inflate(R.layout.item_view_subcategory, parent, false)
        )
    }

    fun updateData(dat: List<CategoryId>) {
        this.dat.clear()
        this.dat.addAll(dat)
        notifyDataSetChanged()
    }

    override fun getItemCount(): Int {
        return dat.size
    }

    override fun onBindViewHolder(holder: SubCategoryViewHolder, position: Int) {
        holder.bind(dat[position])
    }
}