package com.imbana5.unofficialkecipir.model

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

data class StockActive(
    @Expose
    @SerializedName("id_harvest")
    val id_harvest: String? = null,

    @Expose
    @SerializedName("id_petani_barang")
    val id_petani_barang: String? = null,

    @Expose
    @SerializedName("item_id")
    val item_id: String? = null,

    @Expose
    @SerializedName("nama")
    val nama: String? = null,

    @Expose
    @SerializedName("barang")
    val barang: String? = null,

    @Expose
    @SerializedName("tgl_panen")
    val tgl_panen: String? = null,

    @Expose
    @SerializedName("qty_awal")
    val qty_awal: String? = null,

    @Expose
    @SerializedName("qty")
    val qty: String? = null,

    @Expose
    @SerializedName("exp_day")
    val exp_day: String? = null,

    @Expose
    @SerializedName("diff")
    val diff: String? = null,

    @Expose
    @SerializedName("stock_available")
    val stock_available: String? = null,
)