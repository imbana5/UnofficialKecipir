package com.imbana5.unofficialkecipir.model

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

data class Banner(
    @Expose
    @SerializedName("id")
    val id: String? = null,

    @Expose
    @SerializedName("judul")
    val judul: String? = null,

    @Expose
    @SerializedName("url")
    val url: String? = null,

    @Expose
    @SerializedName("image")
    val image: String? = null,

    @Expose
    @SerializedName("type")
    val type: String? = null,

    @Expose
    @SerializedName("param")
    val param: String? = null
)

fun getRevisedUrl (url: String): String {
    var revUrl = url
    val urls = url.split("thumb")
    if (urls.size == 3) { revUrl = urls.get(0) + "thumb" + urls.get(2) }
    return revUrl
}