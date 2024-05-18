package com.example.product.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.appcompat.content.res.AppCompatResources
import androidx.recyclerview.widget.RecyclerView
import com.example.product.R
import com.example.product.databinding.PaymentListItemBinding

class PaymentAdapter(private var context: Context) :
    RecyclerView.Adapter<PaymentAdapter.ViewHolder>() {

    var select = 0

    class ViewHolder(productList: PaymentListItemBinding) :
        RecyclerView.ViewHolder(productList.root) {
        val lineOne = productList.lineOne
        val lineTwo = productList.lineTwo
        val selectImg = productList.select
        val img = productList.img
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) = ViewHolder(
        PaymentListItemBinding.inflate(
            LayoutInflater.from(parent.context), parent, false
        )
    )

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        if (position == 0) {
            holder.lineOne.text = "Credit Card"
            holder.lineTwo.text = "XXXX XXXX XXXX 1234"
            holder.img.setImageDrawable(
                AppCompatResources.getDrawable(
                    context,
                    R.drawable.visa
                )
            )
        } else if (position == 1) {
            holder.lineOne.text = "Bank Account"
            holder.lineTwo.text = "Ending in 1235"
            holder.img.setImageDrawable(
                AppCompatResources.getDrawable(
                    context,
                    R.drawable.bank
                )
            )
        } else {
            holder.lineOne.text = "Paypal"
            holder.lineTwo.text = "paypal@gmail.com"
            holder.img.setImageDrawable(
                AppCompatResources.getDrawable(
                    context,
                    R.drawable.paypal
                )
            )
        }
        if (position == select)
            holder.selectImg.setImageDrawable(
                AppCompatResources.getDrawable(
                    context,
                    R.drawable.radio_select
                )
            )
        else
            holder.selectImg.setImageDrawable(
                AppCompatResources.getDrawable(
                    context,
                    R.drawable.radio_unselect
                )
            )
        holder.itemView.rootView.setOnClickListener {
            select = position
            notifyDataSetChanged()
        }
    }

    override fun getItemCount() = 3
}
