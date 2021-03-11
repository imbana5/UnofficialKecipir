package com.imbana5.unofficialkecipir.viewmodel

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.imbana5.unofficialkecipir.model.*
import com.imbana5.unofficialkecipir.repository.Repository

class MainViewModel: AbstractViewModel() {
    /***********************************************************************************************
     * Variables
     **********************************************************************************************/
    val banners:        LiveData<BannerRawData>     = Repository.getBanners()
    val categories:     LiveData<CategoryRawData>   = Repository.getCategories()

    val allProducts:    MutableLiveData<ArrayList<Product>> = MutableLiveData()
    val promoProducts:  MutableLiveData<ArrayList<Product>> = MutableLiveData()

    var indexAP:        Int = 0
    var indexPP:        Int = 0

    /***********************************************************************************************
     * Public Methods
     **********************************************************************************************/
    fun loadMoreAllProducts() {
        val al0: ArrayList<Product> = allProducts.value ?: arrayListOf()
        val al: ArrayList<Product> = arrayListOf()
        while (al.size < 10 && indexAP < products.value?.data?.size ?: 1) {
            val p = products.value?.data?.get(indexAP++)
            if (p != null) { al.add(p) }
        }
        al0.addAll(al)
        allProducts.postValue(al0)
    }

    fun loadMorePromoProducts() {
        val al0: ArrayList<Product> = promoProducts.value ?: arrayListOf()
        val al: ArrayList<Product> = arrayListOf()
        while (al.size < 10 && indexPP < products.value?.data?.size ?: 1) {
            val p = products.value?.data?.get(indexPP++)
            if (p != null && isPromo(p)) { al.add(p) }
        }
        al0.addAll(al)
        promoProducts.postValue(al0)
    }

    companion object {
        val TAG = MainViewModel::class.simpleName
    }
}