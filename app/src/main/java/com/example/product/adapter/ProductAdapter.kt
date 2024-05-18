package com.example.product.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View.GONE
import android.view.View.VISIBLE
import android.view.ViewGroup
import androidx.appcompat.content.res.AppCompatResources
import androidx.recyclerview.widget.RecyclerView
import com.example.product.databinding.ProductListItemBinding
import com.example.product.model.Product


class ProductAdapter(private var context: Context) :
    RecyclerView.Adapter<ProductAdapter.ViewHolder>() {

    var clickListener: ClickListener? = null
    private var list: List<Product> = listOf()

    fun setProduct(newList: List<Product>) {
        list = newList
    }


    interface ClickListener {
        fun newProductAdded(product: Product)
        fun productRemoved(product: Product)
    }

    class ViewHolder(productList: ProductListItemBinding) :
        RecyclerView.ViewHolder(productList.root) {
        val name = productList.name
        val desc = productList.weight
        val price = productList.price
        val add = productList.add
        val countLT = productList.countLt
        val img = productList.img
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) = ViewHolder(
        ProductListItemBinding.inflate(
            LayoutInflater.from(parent.context), parent, false
        )
    )

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val product = list[position]
        holder.name.text = product.productName
        holder.desc.text = product.desc
        holder.price.text = product.price
        holder.img.setImageDrawable(AppCompatResources.getDrawable(context, product.image!!))
        if (product.count == "0") {
            holder.add.visibility = VISIBLE
            holder.countLT.timeLt.visibility = GONE
        } else {
            holder.add.visibility = GONE
            holder.countLT.timeLt.visibility = VISIBLE
            holder.countLT.count.text = product.count
        }
        holder.add.setOnClickListener {
            product.count = "1"
            clickListener?.newProductAdded(product)
            notifyItemChanged(position)
        }
        holder.countLT.plus.setOnClickListener {
            product.count = (product.count.toInt() + 1).toString()
            clickListener?.newProductAdded(product)
            notifyItemChanged(position)
        }
        holder.countLT.minus.setOnClickListener {
            product.count = (product.count.toInt() - 1).toString()
            clickListener?.productRemoved(product)
            notifyItemChanged(position)
        }
    }

    override fun getItemCount() = list.size

    override fun getItemId(position: Int): Long = list[position].id!!.toLong()
}
