package com.imbana5.unofficialkecipir.viewmodel

import androidx.lifecycle.MutableLiveData
import com.imbana5.unofficialkecipir.model.Product

class ProductViewModel: AbstractViewModel() {
    /***********************************************************************************************
     * Variables
     **********************************************************************************************/
    val selectedProduct:    MutableLiveData<Product> = MutableLiveData()
    val oftenBoughtWiths:   MutableLiveData<ArrayList<Product>> = MutableLiveData()

    /***********************************************************************************************
     * Public Methods
     **********************************************************************************************/
    fun selectProduct(harvestId : String) {
        val ap = products.value?.data?.filter { it.id_harvest.equals(harvestId) }
        selectedProduct.postValue(ap?.get(0)) // if found more than one, take the first element
    }

    fun generateOftenBoughtWiths() {
        val ap = products.value?.data?.filter {
            // Show products from the same farmer (but not the same product)
            it.farmer.equals(selectedProduct.value?.farmer)
            && !it.id_harvest.equals(selectedProduct.value?.id_harvest)
        }
        oftenBoughtWiths.postValue(ap as ArrayList<Product>?)
    }

    companion object {
        val TAG = ProductViewModel::class.simpleName
    }
}