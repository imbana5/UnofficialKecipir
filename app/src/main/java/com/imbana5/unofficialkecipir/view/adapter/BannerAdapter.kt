package com.imbana5.unofficialkecipir.view.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.viewpager.widget.PagerAdapter
import com.imbana5.unofficialkecipir.R
import com.imbana5.unofficialkecipir.model.Banner
import com.imbana5.unofficialkecipir.model.getRevisedUrl
import com.squareup.picasso.Picasso

class BannerAdapter(val banners: MutableList<Banner>) : PagerAdapter() {

    override fun instantiateItem(container: ViewGroup, position: Int): Any {

        val row: View = LayoutInflater.from(container.context)
            .inflate(R.layout.item_banner, container, false)
        val iv: ImageView = row.findViewById(R.id.ivBanner)

        val image = banners.get(position).image?.let { getRevisedUrl(it) }

        Picasso.get().load(image).into(iv);

        container.addView(row)
        return row
    }

    override fun isViewFromObject(view: View, `object`: Any): Boolean {
        return view === `object`
    }

    override fun getCount(): Int {
        return banners.size
    }

    override fun destroyItem(container: ViewGroup, position: Int, view: Any) {
        container.removeView(view as View)
    }
}