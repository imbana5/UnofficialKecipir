package com.imbana5.unofficialkecipir.view.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.imbana5.unofficialkecipir.R
import com.imbana5.unofficialkecipir.model.Category
import com.squareup.picasso.Picasso

class CategoryAdapter(val categories: MutableList<Category>) : RecyclerView.Adapter<CategoryAdapter.ViewHolder>() {

    override fun getItemCount(): Int { return categories.size }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.tv.text = categories[position].category
        Picasso.get().load(categories[position].link).into(holder.iv);
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view: View = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_category, parent, false)
        return ViewHolder(view)
    }

    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val tv: TextView  = itemView.findViewById(R.id.tvCategory)
        val iv: ImageView = itemView.findViewById(R.id.ivCategory)
    }
}