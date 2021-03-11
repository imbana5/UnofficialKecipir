package com.imbana5.unofficialkecipir.api

import com.imbana5.unofficialkecipir.model.BannerRawData
import com.imbana5.unofficialkecipir.model.CategoryRawData
import com.imbana5.unofficialkecipir.model.PageRawData
import com.imbana5.unofficialkecipir.model.ProductRawData
import retrofit2.http.GET
import retrofit2.http.Path

interface ApiService {
    @GET("test/banner.json")
    suspend fun getBanners() : BannerRawData

    @GET("test/category.json")
    suspend fun getCategories() : CategoryRawData

    @GET("test/products.json")
    suspend fun getProducts() : ProductRawData

    @GET("test/page{index}.json")
    suspend fun getPage(@Path("index") index: String) : PageRawData
}