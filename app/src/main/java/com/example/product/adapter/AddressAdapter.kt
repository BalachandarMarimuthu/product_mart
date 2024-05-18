package com.example.product.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.appcompat.content.res.AppCompatResources
import androidx.recyclerview.widget.RecyclerView
import com.example.product.R
import com.example.product.databinding.AddressListItemBinding

class AddressAdapter(private var context: Context) :
    RecyclerView.Adapter<AddressAdapter.ViewHolder>() {

    var select = 0

    class ViewHolder(productList: AddressListItemBinding) :
        RecyclerView.ViewHolder(productList.root) {
        val lineOne = productList.lineOne
        val lineTwo = productList.lineTwo
        val selectImg = productList.select
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) = ViewHolder(
        AddressListItemBinding.inflate(
            LayoutInflater.from(parent.context), parent, false
        )
    )

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        if (position == 0) {
            holder.lineOne.text = "Chloe B Bird"
            holder.lineTwo.text = "87 Great North Road, ALTON"
        } else {
            holder.lineOne.text = "Rich P. Jeffrey"
            holder.lineTwo.text = "4310, Clover Drive Colorado Springs, 80903"
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

    override fun getItemCount() = 2
}
