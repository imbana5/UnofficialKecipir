package com.imbana5.unofficialkecipir.model

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

data class Category(
    @Expose
    @SerializedName("id")
    val id: String? = null,

    @Expose
    @SerializedName("title")
    val title: String? = null,

    @Expose
    @SerializedName("category")
    val category: String? = null,

    @Expose
    @SerializedName("link")
    val link: String? = null,

    @Expose
    @SerializedName("meta_description")
    val meta_description: String? = null,

    @Expose
    @SerializedName("block")
    val block: String? = null,

    @Expose
    @SerializedName("countdown")
    val countdown: String? = null,

    @Expose
    @SerializedName("poster")
    val poster: String? = null,

    @Expose
    @SerializedName("item")
    val item: String? = null,

    @Expose
    @SerializedName("ordering")
    val ordering: String? = null
)

fun toArrayListCategory(array: Array<Category>) : ArrayList<Category> {
    return array.toCollection(ArrayList())
}