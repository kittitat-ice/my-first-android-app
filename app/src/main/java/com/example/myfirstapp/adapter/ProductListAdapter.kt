package com.example.myfirstapp.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.navigation.findNavController
import androidx.recyclerview.widget.RecyclerView
import com.example.myfirstapp.ProductListScreenDirections
import com.example.myfirstapp.R
import com.example.myfirstapp.api.product.ProductListJson
import java.text.NumberFormat

class ProductListAdapter(private val dataSet: ProductListJson) :
  RecyclerView.Adapter<ProductListAdapter.ViewHolder>() {

  class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
    val productName: TextView = view.findViewById(R.id.product_list_product_name)
    val productPrice: TextView = view.findViewById(R.id.product_list_product_price)
    val email: TextView = view.findViewById(R.id.product_list_email)
    val website: TextView = view.findViewById(R.id.product_list_website)
    val clickable: ViewGroup = view.findViewById(R.id.product_list_clickable)
  }

  override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
    val view = LayoutInflater.from(parent.context)
      .inflate(R.layout.product_listitem, parent, false)

    return ViewHolder(view)
  }

  override fun onBindViewHolder(holder: ViewHolder, position: Int) {

    val item = dataSet[position]

    holder.productName.text = item.name
    holder.email.text = item.email
    holder.website.text = item.website
    holder.productPrice.text =
      "฿${NumberFormat.getNumberInstance().format((1000..10000).random())}"

    holder.clickable.setOnClickListener { view ->
      val action = ProductListScreenDirections.goToProductDetailScreen(item)
      view.findNavController().navigate(action)
    }
  }

  override fun getItemCount() = dataSet.size

}