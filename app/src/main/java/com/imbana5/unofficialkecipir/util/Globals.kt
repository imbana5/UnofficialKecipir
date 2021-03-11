package com.imbana5.unofficialkecipir.util

import android.content.res.Resources
import com.imbana5.unofficialkecipir.model.Banner
import com.imbana5.unofficialkecipir.model.Category
import com.imbana5.unofficialkecipir.model.Product
import java.util.*
import kotlin.collections.ArrayList

object Globals {

    fun Int.toDp(): Int = (this / Resources.getSystem().displayMetrics.density).toInt()
    fun Int.toPx(): Int = (this * Resources.getSystem().displayMetrics.density).toInt()
    fun String.addThousandSeparator():
            String = String.format("%,d", this.toInt()).replace(",", ".")

    fun drawDots(index: Int, length: Int) : String {
        var dots = "";
        for (i in 0..length-1) {
            if (index == i) { dots += "●" }
            else { dots += "○" }
        }
        return dots
    }

    fun toArrayList(array: Array<Banner>?) : ArrayList<Banner>? {
        return array?.toCollection(ArrayList())
    }

    fun toArrayList(array: Array<Category>?) : ArrayList<Category>? {
        return array?.toCollection(ArrayList())
    }

    fun toArrayList(array: Array<Product>?) : ArrayList<Product>? {
        return array?.toCollection(ArrayList())
    }

    val ID_HARVEST = "id_harvest"
    val TITLE = "title"
}