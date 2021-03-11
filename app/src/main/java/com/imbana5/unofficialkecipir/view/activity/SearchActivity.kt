package com.imbana5.unofficialkecipir.view.activity

import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import com.imbana5.unofficialkecipir.databinding.ActivitySearchBinding
import com.imbana5.unofficialkecipir.viewmodel.SearchViewModel

class SearchActivity : AppCompatActivity() {
    /***********************************************************************************************
     * Variables
     **********************************************************************************************/
    // View
    private lateinit var binding: ActivitySearchBinding

    // ViewModel
    lateinit var sVM: SearchViewModel

    /***********************************************************************************************
     * Lifecycle
     **********************************************************************************************/
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setViewBinding()
        setClickables()
        setObservers()
    }

    override fun onDestroy() {
        super.onDestroy()
        sVM.cancelJobs()
    }

    /***********************************************************************************************
     * Init
     **********************************************************************************************/
    private fun setClickables() {
        binding.clSearchBox.setOnClickListener { Log.d(TAG, "Search box clicked"); }
    }

    private fun setObservers() {
        sVM = ViewModelProvider(this).get(SearchViewModel::class.java)

        sVM.products.observe(this, {
            Log.d(TAG, it.data.toString())
        })
    }

    private fun setViewBinding() {
        binding = ActivitySearchBinding.inflate(layoutInflater)
        setContentView(binding.root)
    }

    /***********************************************************************************************
     * Statics
     **********************************************************************************************/
    companion object {
        val TAG = SearchActivity::class.simpleName
    }
}