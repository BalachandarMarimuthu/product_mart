package com.example.product.history

import android.app.Activity
import android.os.Bundle
import android.view.View
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.product.R
import com.example.product.adapter.OrderAdapter
import com.example.product.databinding.ActivityHistoryBinding
import com.example.product.main.MainActivity
import com.example.product.utils.Utility

class HistoryActivity : Activity() {

    private lateinit var binding: ActivityHistoryBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityHistoryBinding.inflate(layoutInflater)
        setContentView(binding.root)
        setupToolbar()
        setupRecyclerView()
    }

    private fun goHome() = Utility.gotoIntentFinish(this@HistoryActivity, MainActivity::class.java)

    private fun setupToolbar() {
        binding.toolbar.toolTitle.text = getString(R.string.history)
        binding.toolbar.toolBack.visibility = View.VISIBLE
        binding.toolbar.toolBack.setOnClickListener { goHome() }
    }

    private fun setupRecyclerView() {
        binding.orderList.layoutManager = LinearLayoutManager(this@HistoryActivity)
        binding.orderList.adapter = OrderAdapter(this)
    }

    override fun onBackPressed() {
        goHome()
    }
}
