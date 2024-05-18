package com.example.product.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View.GONE
import android.view.View.VISIBLE
import android.view.ViewGroup
import androidx.appcompat.content.res.AppCompatResources
import androidx.recyclerview.widget.RecyclerView
import com.example.product.databinding.ProductListItemBinding
import com.example.product.model.Cart
import com.example.product.model.Product

class CartAdapter(private var context: Context) : RecyclerView.Adapter<CartAdapter.ViewHolder>() {

    var clickListener: ClickListener? = null
    var list: HashMap<String, Product>? = null

    interface ClickListener {
        fun emptyCart()
        fun updatePrice()
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
        val product = getProductFromHashMap(position)
        holder.name.text = product.productName
        holder.desc.text = product.desc
        if (product.count == "1")
            holder.price.text = product.price
        else {
            val qty = product.count.toInt()
            var price = product.price!!.split("$")
            holder.price.text = "$ ${(price[1].toDouble() * qty.toDouble()).toInt()}"
        }
        holder.img.setImageDrawable(AppCompatResources.getDrawable(context, product.image!!))
        if (product.count == "0") {
            holder.add.visibility = VISIBLE
            holder.countLT.timeLt.visibility = GONE
        } else {
            holder.add.visibility = GONE
            holder.countLT.timeLt.visibility = VISIBLE
            holder.countLT.count.text = product.count
        }
        holder.countLT.plus.setOnClickListener {
            product.count = (product.count.toInt() + 1).toString()
            notifyItemChanged(position)
            clickListener!!.updatePrice()
        }
        holder.countLT.minus.setOnClickListener {
            if (product.count == "1") {
                if (list!!.size == 1) clickListener!!.emptyCart()
                else {
                    Cart.map.remove(product.id!!)
                    list = Cart.map
                    clickListener!!.updatePrice()
                    notifyDataSetChanged()
                }
            } else {
                product.count = (product.count.toInt() - 1).toString()
                clickListener!!.updatePrice()
                notifyItemChanged(position)
            }
        }
    }

    private fun getProductFromHashMap(pos: Int): Product {
        var count = 0
        var currentProduct: Product? = null
        for (data in list!!) {
            if (count == pos) {
                currentProduct = data.value
                break
            } else count++
        }
        return currentProduct!!
    }

    override fun getItemCount() = list!!.size
}
