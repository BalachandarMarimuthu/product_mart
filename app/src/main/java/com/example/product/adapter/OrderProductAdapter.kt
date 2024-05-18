package com.example.product.adapter

import android.app.Activity
import android.view.LayoutInflater
import android.view.View.GONE
import android.view.View.VISIBLE
import android.view.ViewGroup
import androidx.appcompat.content.res.AppCompatResources
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView
import com.example.product.R
import com.example.product.databinding.ProductListItemBinding
import com.example.product.utils.Utility

class OrderProductAdapter(private var activity: Activity) :
    RecyclerView.Adapter<OrderProductAdapter.ViewHolder>() {

    class ViewHolder(listItem: ProductListItemBinding) : RecyclerView.ViewHolder(listItem.root) {
        val img = listItem.img
        val name = listItem.name
        val weight = listItem.weight
        val price = listItem.price
        val delivered = listItem.delivered
        val qty = listItem.qtyLt
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) = ViewHolder(
        ProductListItemBinding.inflate(
            LayoutInflater.from(parent.context), parent, false
        )
    )

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.img.setImageDrawable(
            AppCompatResources.getDrawable(
                activity,
                Utility.getProductList()[position].image!!
            )
        )
        holder.weight.visibility = GONE
        holder.delivered.visibility = VISIBLE
        holder.qty.visibility = VISIBLE
        holder.name.text = Utility.getProductList()[position].productName!!
        holder.price.text = Utility.getProductList()[position].price!!
        holder.price.setTextColor(ContextCompat.getColor(activity, R.color.yellow_text))
    }

    override fun getItemCount() = 4
}
