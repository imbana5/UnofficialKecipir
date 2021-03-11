package com.imbana5.unofficialkecipir.model

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

data class BannerRawData(
    @Expose
    @SerializedName("code")
    val code: String? = null,

    @Expose
    @SerializedName("message")
    val message: String? = null,

    @Expose
    @SerializedName("data")
    val data: Array<Banner>? = null
) {
    override fun toString(): String {
        return "RawData(code=$code, message=$message, data=$data)"
    }
}