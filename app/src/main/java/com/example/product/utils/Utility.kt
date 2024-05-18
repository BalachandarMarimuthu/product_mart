package com.example.product.utils

import android.app.Activity
import android.content.Intent
import com.example.product.R
import com.example.product.model.Product

object Utility {

    fun gotoIntent(activity: Activity, cls: Class<*>) {
        activity.startActivity(Intent(activity, cls))
    }

    fun gotoIntentFinish(activity: Activity, cls: Class<*>) {
        activity.startActivity(Intent(activity, cls))
        activity.finish()
    }

    fun gotoIntentFinishAll(activity: Activity, cls: Class<*>) {
        activity.startActivity(Intent(activity, cls))
        activity.finishAffinity()
    }

    fun getProductList(): List<Product> {
        val productList = mutableListOf<Product>()
        productList.add(
            Product(
                "1", R.drawable.product_one, "Beauty Products", "$120", "$50", "0", "New Product"
            )
        )
        productList.add(
            Product(
                "2", R.drawable.product_two, "Watches", "$720", "$450", "0", "Trendy collection"
            )
        )
        productList.add(
            Product(
                "3", R.drawable.product_three, "Camera", "$1290", "$950", "0", "Antique"
            )
        )
        productList.add(
            Product(
                "4", R.drawable.product_four, "Grocery", "$510", "$400", "0", "Daily Needs"
            )
        )
        productList.add(
            Product(
                "5", R.drawable.product_five, "PC", "$2310", "$1400", "0", "Office Accessories"
            )
        )
        productList.add(
            Product(
                "6", R.drawable.product_six, "Fashion", "$330", "$260", "0", "Clothes"
            )
        )
        productList.add(
            Product(
                "7", R.drawable.product_seven, "Stationary", "$110", "$78", "0", "Students Needs"
            )
        )
        productList.add(
            Product(
                "8", R.drawable.product_eight, "Fruits", "$99", "$85", "0", "Healthy"
            )
        )
        productList.add(
            Product(
                "9", R.drawable.prouct_nine, "Cosmetics", "$837", "$710", "0", "Makeup Needs"
            )
        )
        productList.add(
            Product(
                "10", R.drawable.product_ten, "Dairy Products", "$78", "$60", "0", "Dairy"
            )
        )
        return productList
    }

}
