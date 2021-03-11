package com.imbana5.unofficialkecipir.view.adapter

import android.content.Intent
import android.graphics.Paint
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.imbana5.unofficialkecipir.R
import com.imbana5.unofficialkecipir.model.Product
import com.imbana5.unofficialkecipir.model.isPromo
import com.imbana5.unofficialkecipir.util.Globals.ID_HARVEST
import com.imbana5.unofficialkecipir.util.Globals.TITLE
import com.imbana5.unofficialkecipir.util.Globals.addThousandSeparator
import com.imbana5.unofficialkecipir.view.activity.ProductActivity
import com.squareup.picasso.Picasso
import java.util.*


class ProductAdapter(val products: ArrayList<Product>) :
        RecyclerView.Adapter<ProductAdapter.ViewHolder>() {

    override fun getItemCount(): Int { return products.size }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val p = products[position]
        Picasso.get().load(p.photo).into(holder.iv)
        holder.tvFarmer.text = p.farmer
        holder.tvTitle.text = p.title
        holder.tvDiscount.text = p.discount
        holder.tvPrice.text = String.format(RP, p.promo_price?.addThousandSeparator())

        if (!isPromo(p)) {
            // If no promo, hide discount and price
            holder.tvDiscount.visibility = View.GONE
            holder.tvPrice0.visibility = View.GONE
            return
        } // else promo:
        holder.tvDiscount.visibility = View.VISIBLE
        holder.tvPrice0.visibility = View.VISIBLE
        holder.tvPrice0.text = String.format(RP, p.sell_price?.addThousandSeparator())
        holder.tvPrice0.apply { paintFlags = Paint.STRIKE_THRU_TEXT_FLAG }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view: View = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_product, parent, false)
        return ViewHolder(view)
    }

    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val iv: ImageView = itemView.findViewById(R.id.ivProduct)
        val tvTitle: TextView  = itemView.findViewById(R.id.tvTitle)
        val tvFarmer: TextView  = itemView.findViewById(R.id.tvFarmer)
        val tvPrice0: TextView  = itemView.findViewById(R.id.tvOriginalPrice)
        val tvPrice: TextView  = itemView.findViewById(R.id.tvFinalPrice)
        val tvDiscount: TextView  = itemView.findViewById(R.id.tvDiscount)

        init {
            itemView.setOnClickListener {
                val i = adapterPosition
                Log.d(TAG, "Clicked" + i + " : " + products[i].id_harvest)
                val c = itemView.getContext()
                val intent = Intent(c, ProductActivity::class.java)
                intent.putExtra(ID_HARVEST, products[i].id_harvest)
                intent.putExtra(TITLE, products[i].title)
                c.startActivity(intent)
            }
        }
    }

    companion object {
        val TAG = ProductAdapter::class.simpleName
        val RP = "Rp %s,-"
    }
}