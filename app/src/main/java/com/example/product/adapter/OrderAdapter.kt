package com.example.product.adapter

import android.app.Activity
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.appcompat.content.res.AppCompatResources
import androidx.recyclerview.widget.RecyclerView
import com.example.product.databinding.OrderListItemBinding
import com.example.product.orderDetails.OrderDetails
import com.example.product.utils.Utility

class OrderAdapter(private var activity: Activity) :
    RecyclerView.Adapter<OrderAdapter.ViewHolder>() {

    class ViewHolder(orderList: OrderListItemBinding) : RecyclerView.ViewHolder(orderList.root) {
        val img = orderList.img
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) = ViewHolder(
        OrderListItemBinding.inflate(
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
        holder.itemView.rootView.setOnClickListener {
            Utility.gotoIntent(activity, OrderDetails::class.java)
        }
    }

    override fun getItemCount() = 8
}
