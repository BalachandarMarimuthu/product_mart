package com.example.product.cart

import android.app.Activity
import android.os.Bundle
import android.view.View
import android.view.View.GONE
import android.view.View.VISIBLE
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.SimpleItemAnimator
import com.example.product.R
import com.example.product.adapter.CartAdapter
import com.example.product.checkout.CheckoutActivity
import com.example.product.databinding.ActivityCartBinding
import com.example.product.model.Cart
import com.example.product.utils.Utility

class CartActivity : Activity(), CartAdapter.ClickListener {

    private lateinit var binding: ActivityCartBinding
    private var adapter: CartAdapter? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityCartBinding.inflate(layoutInflater)
        setContentView(binding.root)
        setupToolbar()
        binding.continueBtn.setOnClickListener {
            Utility.gotoIntent(
                this@CartActivity, CheckoutActivity::class.java
            )
        }
    }

    private fun setupToolbar() {
        binding.toolbar.toolTitle.text = getString(R.string.cart)
        binding.toolbar.toolBack.visibility = View.VISIBLE
        binding.toolbar.toolBack.setOnClickListener { finish() }
    }

    override fun onResume() {
        super.onResume()
        setupRecyclerView()
    }

    private fun setupRecyclerView() {
        if (adapter == null) {
            binding.productList.layoutManager = LinearLayoutManager(this@CartActivity)
            adapter = CartAdapter(this)
            adapter!!.list = Cart.map
            adapter!!.clickListener = this
            val animator = binding.productList.itemAnimator
            if (animator is SimpleItemAnimator) {
                animator.supportsChangeAnimations = false
            }
            updatePrice()
        }
        binding.productList.adapter = adapter
    }

    override fun emptyCart() {
        Cart.map.clear()
        binding.productList.visibility = GONE
        binding.continueBtn.visibility = GONE
        binding.noData.noDataLt.visibility = VISIBLE
    }

    override fun updatePrice() {
        var itemPrice = 0
        for (data in Cart.map) {
            val price = data.value.price!!.split("$")
            itemPrice += (price[1].toInt() * data.value.count.toInt())
        }
        binding.itemTotal.text = "$ ${itemPrice}"
        binding.serviceCharges.text = "$70"
        binding.total.text = "$ ${(itemPrice + 70)}"
    }
}
