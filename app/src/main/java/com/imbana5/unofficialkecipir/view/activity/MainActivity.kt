package com.imbana5.unofficialkecipir.view.activity

import android.app.SearchManager
import android.content.Context
import android.os.Bundle
import android.os.Handler
import android.util.Log
import android.view.Menu
import android.widget.SearchView
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.viewpager.widget.ViewPager
import com.imbana5.unofficialkecipir.R
import com.imbana5.unofficialkecipir.databinding.ActivityMainBinding
import com.imbana5.unofficialkecipir.model.Banner
import com.imbana5.unofficialkecipir.model.Category
import com.imbana5.unofficialkecipir.model.Product
import com.imbana5.unofficialkecipir.util.Globals
import com.imbana5.unofficialkecipir.util.Globals.toArrayList
import com.imbana5.unofficialkecipir.view.adapter.BannerAdapter
import com.imbana5.unofficialkecipir.view.adapter.MarginItemDecoration
import com.imbana5.unofficialkecipir.viewmodel.MainViewModel
import com.imbana5.unofficialkecipir.view.adapter.CategoryAdapter
import com.imbana5.unofficialkecipir.view.adapter.ProductAdapter
import java.util.*

class MainActivity : AppCompatActivity() {
    /***********************************************************************************************
     * Variables
     **********************************************************************************************/
    // Models
    private lateinit var allProducts:   ArrayList<Product>
    private lateinit var promoProducts: ArrayList<Product>

    // View
    private lateinit var binding: ActivityMainBinding

    // ViewModel
    lateinit var mVM: MainViewModel

    /***********************************************************************************************
     * Lifecycle
     **********************************************************************************************/
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        initViewModels()
        initViews()
        initClickables()
        initObservers()
    }

    override fun onDestroy() {
        super.onDestroy()
        mVM.cancelJobs()
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        menuInflater.inflate(R.menu.options_menu, menu)

        val searchManager = getSystemService(Context.SEARCH_SERVICE) as SearchManager
        (menu.findItem(R.id.search).actionView as SearchView).apply {
            setSearchableInfo(searchManager.getSearchableInfo(componentName))
        }

        return true
    }

    /***********************************************************************************************
     * Init
     **********************************************************************************************/
    private fun initClickables() {
        binding.clShippingDate.setOnClickListener { Log.d(TAG, "Shipping date clicked"); }
        binding.btDebug.setOnClickListener {
            mVM.loadMoreAllProducts()
            mVM.loadMorePromoProducts()
        }
    }

    private fun initObservers() {
        mVM.banners.observe(this, { populateBanners(toArrayList(it.data)!!) })
        mVM.categories.observe(this, { populateCategories(toArrayList(it.data)!!) })
        mVM.products.observe(this, { mVM.loadMorePromoProducts(); mVM.loadMoreAllProducts() })

        mVM.promoProducts.observe(this, {
            promoProducts = it
            populateProducts(binding.rvPromoProducts, promoProducts)
        })

        mVM.allProducts.observe(this, {
            allProducts = it
            populateProducts(binding.rvAllProducts, allProducts)
        })
    }

    private fun initViewModels() {
        mVM = ViewModelProvider(this).get(MainViewModel::class.java)
    }

    private fun initViews() {
        // Binding
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
    }

    /***********************************************************************************************
     * Private methods
     **********************************************************************************************/
    private fun populateBanners(banners: ArrayList<Banner>) {
        var currentPage = 0; val n = banners.size

        val vp = binding.vpBanners
        binding.vpBanners.adapter = BannerAdapter(banners)
        binding.vpBanners.addOnPageChangeListener(object : ViewPager.OnPageChangeListener {
            override fun onPageScrollStateChanged(state: Int) {}
            override fun onPageScrolled(position: Int, offset: Float, offsetPixels: Int) {}
            override fun onPageSelected(position: Int) {
                binding.tvBannerDots.text = Globals.drawDots(position, n)
                currentPage = position
            }
        })

        // Auto-swipe banner
        val h = Handler()
        val r = Runnable { vp.setCurrentItem((currentPage++ % n), true) }
        val t = Timer()
        t.schedule(object : TimerTask() {
            override fun run() { h.post(r) }
        }, 500, 3000)
    }

    private fun populateCategories(categories: ArrayList<Category>) {
        val vt = binding.rvCategoriesTop; val vb = binding.rvCategoriesBot
        for (v in arrayOf(vt, vb)) {
            v.layoutManager = LinearLayoutManager(this, RecyclerView.HORIZONTAL, false)
        }

        // Put categories into two rows
        val n = categories.size; val iHalf = Math.ceil(0.5 * n).toInt()
        val categoriesTop = categories.subList(0, iHalf - 1)
        val categoriesBot = categories.subList(iHalf, n - 1)

        vt.adapter = CategoryAdapter(categoriesTop)
        vb.adapter = CategoryAdapter(categoriesBot)
    }

    private fun populateProducts(rv: RecyclerView, products: ArrayList<Product>) {
        if (rv.adapter == null) { // Initiate
            rv.layoutManager = LinearLayoutManager(this, RecyclerView.HORIZONTAL, false)
            rv.addItemDecoration( MarginItemDecoration(resources.getDimensionPixelSize(R.dimen.default_space_padding)) )
            rv.adapter = ProductAdapter(products)
        } else { // Update
            rv.adapter!!.notifyDataSetChanged()
        }
    }

    /***********************************************************************************************
     * Statics
     **********************************************************************************************/
    companion object {
        val TAG = MainActivity::class.simpleName
    }
}