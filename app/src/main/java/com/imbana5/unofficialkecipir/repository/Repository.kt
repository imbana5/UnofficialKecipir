package com.imbana5.unofficialkecipir.repository

import androidx.lifecycle.LiveData
import com.imbana5.unofficialkecipir.api.MainRetrofitBuilder
import com.imbana5.unofficialkecipir.model.BannerRawData
import com.imbana5.unofficialkecipir.model.CategoryRawData
import com.imbana5.unofficialkecipir.model.PageRawData
import com.imbana5.unofficialkecipir.model.ProductRawData
import kotlinx.coroutines.*
import kotlinx.coroutines.Dispatchers.IO
import kotlinx.coroutines.Dispatchers.Main

object Repository {
    /***********************************************************************************************
     * Variables
     **********************************************************************************************/
    var job: CompletableJob? = null

    /***********************************************************************************************
     * Public methods
     **********************************************************************************************/
    fun getBanners(): LiveData<BannerRawData> {
        job = Job()
        return object: LiveData<BannerRawData>(){
            override fun onActive() {
                super.onActive()
                job?.let{ theJob ->
                    CoroutineScope(IO + theJob).launch {
                        withContext(Main){
                            value = MainRetrofitBuilder.apiService.getBanners()
                            theJob.complete()
                        }
                    }
                }
            }
        }
    }

    fun getCategories(): LiveData<CategoryRawData> {
        job = Job()
        return object: LiveData<CategoryRawData>(){
            override fun onActive() {
                super.onActive()
                job?.let{ theJob ->
                    CoroutineScope(IO + theJob).launch {
                        withContext(Main){
                            value = MainRetrofitBuilder.apiService.getCategories()
                            theJob.complete()
                        }
                    }
                }
            }
        }
    }

    fun getProducts(): LiveData<ProductRawData> {
        job = Job()
        return object: LiveData<ProductRawData>(){
            override fun onActive() {
                super.onActive()
                job?.let{ theJob ->
                    CoroutineScope(IO + theJob).launch {
                        withContext(Main){
                            value = MainRetrofitBuilder.apiService.getProducts()
                            theJob.complete()
                        }
                    }
                }
            }
        }
    }

    fun getPage(index: String): LiveData<PageRawData> {
        job = Job()
        return object: LiveData<PageRawData>(){
            override fun onActive() {
                super.onActive()
                job?.let{ theJob ->
                    CoroutineScope(IO + theJob).launch {
                        withContext(Main){
                            value = MainRetrofitBuilder.apiService.getPage(index)
                            theJob.complete()
                        }
                    }
                }
            }
        }
    }

    fun cancelJobs(){
        job?.cancel()
    }
}