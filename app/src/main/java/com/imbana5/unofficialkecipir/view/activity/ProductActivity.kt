package com.imbana5.unofficialkecipir.view.activity

import android.app.SearchManager
import android.content.Context
import android.graphics.Color
import android.graphics.Paint
import android.os.Bundle
import android.util.Log
import android.view.Menu
import android.view.View
import android.widget.SearchView
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.imbana5.unofficialkecipir.R
import com.imbana5.unofficialkecipir.databinding.ActivityProductBinding
import com.imbana5.unofficialkecipir.model.Product
import com.imbana5.unofficialkecipir.model.isPromo
import com.imbana5.unofficialkecipir.util.Globals.ID_HARVEST
import com.imbana5.unofficialkecipir.util.Globals.TITLE
import com.imbana5.unofficialkecipir.util.Globals.addThousandSeparator
import com.imbana5.unofficialkecipir.view.adapter.MarginItemDecoration
import com.imbana5.unofficialkecipir.view.adapter.ProductAdapter
import com.imbana5.unofficialkecipir.view.adapter.ProductAdapter.Companion.RP
import com.imbana5.unofficialkecipir.viewmodel.ProductViewModel
import com.squareup.picasso.Picasso
import java.util.ArrayList

class ProductActivity : AppCompatActivity() {
    /***********************************************************************************************
     * Variables
     **********************************************************************************************/
    // Models
    private var harvestId:  String? = null
    private var title:      String? = null

    // View
    private lateinit var binding: ActivityProductBinding

    // ViewModel
    lateinit var pVM: ProductViewModel

    /***********************************************************************************************
     * Lifecycle
     **********************************************************************************************/
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        initExtras()
        initViewModels()
        initViews()
        initClickables()
        initObservers()
    }

    override fun onDestroy() {
        super.onDestroy()
        pVM.cancelJobs()
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
    }

    private fun initObservers() {
        pVM.products.observe(this, {
            if (harvestId != null) { pVM.selectProduct(harvestId!!) }
        })

        pVM.selectedProduct.observe(this, {
            fillProductDetails(it)
            pVM.generateOftenBoughtWiths()
        })

        pVM.oftenBoughtWiths.observe(this, {
            if (it.isEmpty()) {
                binding.lyOftenBoughtWith.visibility = View.GONE
            } else {
                binding.lyOftenBoughtWith.visibility = View.VISIBLE
                populateOftenBoughtWiths(binding.rvOftenBoughtWith, it)
            }
        })
    }

    private fun initExtras() {
        harvestId = intent.getStringExtra(ID_HARVEST)
        title = intent.getStringExtra(TITLE)
        getSupportActionBar()?.setTitle(title);
    }

    private fun initViewModels() {
        Log.d(TAG, "id_harvest: " + harvestId)
        pVM = ViewModelProvider(this).get(ProductViewModel::class.java)
    }

    private fun initViews() {
        // Binding
        binding = ActivityProductBinding.inflate(layoutInflater)
        setContentView(binding.root)
    }

    /***********************************************************************************************
     * Private methods
     **********************************************************************************************/
    private fun fillProductDetails(product: Product) {
        Picasso.get().load(product.photo).into(binding.ivProduct)
        binding.tvFarmer.text = product.farmer
        binding.tvTitle.text = product.title
        binding.tvDiscount.text = product.discount
        binding.tvFinalPrice.text = String.format(RP, product.promo_price?.addThousandSeparator())
        binding.tvGrade.text = product.grade
        binding.tvGrade.setBackgroundColor(Color.parseColor(product.grade_color))

        if (!isPromo(product)) {
            binding.tvDiscount.visibility = View.GONE
            binding.tvOriginalPrice.visibility = View.GONE
            return
        }
        binding.tvDiscount.visibility = View.VISIBLE
        binding.tvOriginalPrice.visibility = View.VISIBLE
        binding.tvOriginalPrice.text = String.format(RP, product.sell_price?.addThousandSeparator())
        binding.tvOriginalPrice.apply { paintFlags = Paint.STRIKE_THRU_TEXT_FLAG }
    }

    private fun populateOftenBoughtWiths(rv: RecyclerView, products: ArrayList<Product>) {
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
        val TAG = ProductActivity::class.simpleName
    }
}