package com.imbana5.unofficialkecipir.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.imbana5.unofficialkecipir.model.ProductRawData
import com.imbana5.unofficialkecipir.repository.Repository

open class AbstractViewModel: ViewModel() {
    /***********************************************************************************************
     * Variables
     **********************************************************************************************/
    val products: LiveData<ProductRawData> = Repository.getProducts()

    /***********************************************************************************************
     * Public Methods
     **********************************************************************************************/
    fun cancelJobs() { Repository.cancelJobs() }
}