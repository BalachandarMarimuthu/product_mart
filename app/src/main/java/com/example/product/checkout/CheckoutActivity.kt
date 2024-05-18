package com.example.product.checkout

import android.app.Activity
import android.app.Dialog
import android.os.Bundle
import android.view.View
import android.view.Window
import android.widget.Button
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.product.R
import com.example.product.adapter.AddressAdapter
import com.example.product.adapter.PaymentAdapter
import com.example.product.databinding.ActivityCheckoutBinding
import com.example.product.history.HistoryActivity
import com.example.product.model.Cart
import com.example.product.utils.Utility


class CheckoutActivity : Activity() {

    private lateinit var binding: ActivityCheckoutBinding
    var home = false
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityCheckoutBinding.inflate(layoutInflater)
        setContentView(binding.root)
        setupToolbar()
        setupRecyclerView()
        binding.continueBtn.setOnClickListener {
            showDialog()
        }
    }

    private fun setupToolbar() {
        binding.toolbar.toolTitle.text = getString(R.string.checkout)
        binding.toolbar.toolBack.visibility = View.VISIBLE
        binding.toolbar.toolBack.setOnClickListener { finish() }
    }

    private fun setupRecyclerView() {
        binding.addressList.layoutManager = LinearLayoutManager(this@CheckoutActivity)
        binding.addressList.adapter = AddressAdapter(this)
        binding.paymentList.layoutManager = LinearLayoutManager(this@CheckoutActivity)
        binding.paymentList.adapter = PaymentAdapter(this)
    }

    private fun showDialog() {
        val dialog = Dialog(this)
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE)
        dialog.setCancelable(false)
        dialog.setContentView(R.layout.success_popup)
        val goBtn: Button = dialog.findViewById(R.id.continue_btn)
        goBtn.setOnClickListener {
            Cart.map.clear()
            Utility.gotoIntentFinishAll(this@CheckoutActivity, HistoryActivity::class.java)
        }
        dialog.show()
    }
}
