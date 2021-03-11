package com.imbana5.unofficialkecipir.model

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

data class Page(
    @Expose
    @SerializedName("id_product")
    val id_product: String? = null,

    @Expose
    @SerializedName("id_harvest")
    val id_harvest: String? = null,

    @Expose
    @SerializedName("farmer_discount")
    val farmer_discount: String? = null,

    @Expose
    @SerializedName("id_category")
    val id_category: String? = null,

    @Expose
    @SerializedName("id_grade")
    val id_grade: String? = null,

    @Expose
    @SerializedName("farmer")
    val farmer: String? = null,

    @Expose
    @SerializedName("exp_day")
    val exp_day: String? = null,

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
    @SerializedName("id_farmer")
    val id_farmer: String? = null,

    @Expose
    @SerializedName("buy_price")
    val buy_price: String? = null,

    @Expose
    @SerializedName("sell_price")
    val sell_price: String? = null,

    @Expose
    @SerializedName("promo_price")
    val promo_price: String? = null,

    @Expose
    @SerializedName("sell_price_text")
    val sell_price_text: String? = null,

    @Expose
    @SerializedName("sell_price_color")
    val sell_price_color: String? = null,

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
    @SerializedName("is_stock")
    val is_stock: String? = null,

    @Expose
    @SerializedName("stock_active")
    val stock_active: Array<StockActive>? = null,

    @Expose
    @SerializedName("stock")
    val stock: String? = null,

    @Expose
    @SerializedName("qty_chart")
    val qty_chart: String? = null,

    @Expose
    @SerializedName("stock_text")
    val stock_text: String? = null,

    @Expose
    @SerializedName("stock_color")
    val stock_color: String? = null,

    @Expose
    @SerializedName("rating")
    val rating: String? = null,

    @Expose
    @SerializedName("social_buying")
    val social_buying: String? = null,
)

fun isPromo (page: Page): Boolean {
    return !page.discount.equals("0%")
}