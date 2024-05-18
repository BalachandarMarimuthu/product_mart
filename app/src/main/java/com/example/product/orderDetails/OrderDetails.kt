package com.example.product.orderDetails

import android.app.Activity
import android.os.Bundle
import android.view.View
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.product.R
import com.example.product.adapter.OrderProductAdapter
import com.example.product.databinding.ActivityOrderDetailsBinding

class OrderDetails : Activity() {

    private lateinit var binding: ActivityOrderDetailsBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityOrderDetailsBinding.inflate(layoutInflater)
        setContentView(binding.root)
        setupToolbar()
        setupRecyclerView()
    }

    private fun setupToolbar() {
        binding.toolbar.toolTitle.text = getString(R.string.order_details)
        binding.toolbar.toolBack.visibility = View.VISIBLE
        binding.toolbar.toolBack.setOnClickListener { finish() }
    }

    private fun setupRecyclerView() {
        binding.productList.layoutManager = LinearLayoutManager(this@OrderDetails)
        binding.productList.adapter = OrderProductAdapter(this)
    }
}
