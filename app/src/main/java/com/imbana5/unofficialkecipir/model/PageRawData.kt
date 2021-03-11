package com.imbana5.unofficialkecipir.model

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

data class PageRawData(
    @Expose
    @SerializedName("code")
    val code: String? = null,

    @Expose
    @SerializedName("message")
    val message: String? = null,

    @Expose
    @SerializedName("data")
    val data: Array<Page>? = null
) {
    override fun toString(): String {
        return "RawData(code=$code, message=$message, data=$data)"
    }
}