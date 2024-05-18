package com.example.product.main

import android.app.Activity
import android.os.Bundle
import android.view.View.GONE
import android.view.View.VISIBLE
import android.window.OnBackInvokedDispatcher
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.SimpleItemAnimator
import com.example.product.R
import com.example.product.adapter.ProductAdapter
import com.example.product.cart.CartActivity
import com.example.product.databinding.ActivityMainBinding
import com.example.product.history.HistoryActivity
import com.example.product.model.Cart
import com.example.product.model.Product
import com.example.product.utils.Utility
import com.example.product.utils.Utility.getProductList

class MainActivity : Activity(), ProductAdapter.ClickListener {

    private lateinit var binding: ActivityMainBinding
    private var adapter: ProductAdapter? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        setupToolbar()
        binding.bottom.root.setOnClickListener {
            Utility.gotoIntent(this@MainActivity, CartActivity::class.java)
        }
    }

    private fun setupToolbar() {
        binding.toolbar.toolTitle.text = getString(R.string.products)
        binding.toolbar.history.visibility = VISIBLE
        binding.toolbar.history.setOnClickListener {
            Utility.gotoIntentFinish(this@MainActivity, HistoryActivity::class.java)
        }
    }

    override fun onResume() {
        super.onResume()
        if (adapter == null) setupRecyclerView()
        else updateProductAndCart()
        bottomCartData()
    }

    private fun updateProductAndCart() {
        if (Cart.map.size == 0) {
            setupRecyclerView()
        } else {
            val newProducts = getProductList()
            for (data in Cart.map) {
                val product = newProducts.filter {
                    it.id == data.value.id
                }
                newProducts[newProducts.indexOf(product[0])].count = data.value.count
            }
            addAdapter(newProducts)
        }
    }

    private fun addAdapter(list: List<Product>) {
        adapter!!.clickListener = this
        adapter!!.setProduct(list)
        binding.productList.adapter = adapter
    }

    private fun setupRecyclerView() {
        if (adapter == null) {
            binding.productList.layoutManager = LinearLayoutManager(this@MainActivity)
            adapter = ProductAdapter(this)
            val animator = binding.productList.itemAnimator
            if (animator is SimpleItemAnimator) {
                animator.supportsChangeAnimations = false
            }
        }
        if (Cart.map.isNotEmpty()) updateProductAndCart()
        else addAdapter(getProductList())
    }

    override fun newProductAdded(product: Product) {
        if (Cart.map.containsKey(product.id!!)) updateCart(product)
        else Cart.map[product.id!!] = product
        bottomCartData()
    }

    override fun productRemoved(product: Product) {
        if (product.count == "0") Cart.map.remove(product.id!!)
        else updateCart(product)
        bottomCartData()
    }

    private fun bottomCartData() {
        if (Cart.map.size == 0) {
            binding.bottom.root.visibility = GONE
        } else {
            if (Cart.map.size == 1)
                binding.bottom.itemsAdded.text =
                    getString(R.string.item_added, Cart.map.size.toString())
            else
                binding.bottom.itemsAdded.text =
                    getString(R.string.items_added, Cart.map.size.toString())
            binding.bottom.root.visibility = VISIBLE
        }
    }

    private fun updateCart(product: Product) {
        Cart.map[product.id!!]?.count = product.count
    }

    override fun getOnBackInvokedDispatcher(): OnBackInvokedDispatcher {
        finishAffinity()
        return super.getOnBackInvokedDispatcher()
    }
}
