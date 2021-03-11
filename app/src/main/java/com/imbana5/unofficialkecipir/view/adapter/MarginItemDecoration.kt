package com.imbana5.unofficialkecipir.view.adapter

import android.graphics.Rect
import android.view.View
import androidx.recyclerview.widget.RecyclerView

class MarginItemDecoration(private val spacing: Int) : RecyclerView.ItemDecoration() {
    override fun getItemOffsets(
            outRect: Rect, view: View,
            parent: RecyclerView,
            state: RecyclerView.State
    ) {
        with(outRect) {
            if (parent.getChildAdapterPosition(view) == 0) { left = spacing }
            top = spacing
            right = spacing
            bottom = spacing
        }
    }
}