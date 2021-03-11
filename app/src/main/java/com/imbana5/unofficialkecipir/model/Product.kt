package com.imbana5.unofficialkecipir.model

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

data class Product(
    @Expose
    @SerializedName("id_harvest")
    val id_harvest: String? = null,

    @Expose
    @SerializedName("id_product_farmer")
    val id_product_farmer: String? = null,

    @Expose
    @SerializedName("farmer")
    val farmer: String? = null,

    @Expose
    @SerializedName("harvest_date")
    val harvest_date: String? = null,

    @Expose
    @SerializedName("title")
    val title: String? = null,

    @Expose
    @SerializedName("title_en")
    val title_en: String? = null,

    @Expose
    @SerializedName("code")
    val code: String? = null,

    @Expose
    @SerializedName("share_link")
    val share_link: String? = null,

    @Expose
    @SerializedName("photo")
    val photo: String? = null,

    @Expose
    @SerializedName("sell_price")
    val sell_price: String? = null,

    @Expose
    @SerializedName("promo_price")
    val promo_price: String? = null,

    @Expose
    @SerializedName("discount")
    val discount: String? = null,

    @Expose
    @SerializedName("unit")
    val unit: String? = null,

    @Expose
    @SerializedName("grade")
    val grade: String? = null,

    @Expose
    @SerializedName("grade_note")
    val grade_note: String? = null,

    @Expose
    @SerializedName("grade_color")
    val grade_color: String? = null,

    @Expose
    @SerializedName("stock")
    val stock: String? = null,

    @Expose
    @SerializedName("qty_chart")
    val qty_chart: String? = null,

    @Expose
    @SerializedName("rating")
    val rating: String? = null,
)

fun isPromo (product: Product): Boolean {
    return !product.discount.equals("0%")
}