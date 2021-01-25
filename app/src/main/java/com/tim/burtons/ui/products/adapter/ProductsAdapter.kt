package com.tim.burtons.ui.products.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.Filter
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.tim.burtons.R
import com.tim.burtons.databinding.LayoutProductsItemBinding
import com.tim.burtons.model.Product


class ProductsAdapter(private val products: ArrayList<Product>) : RecyclerView.Adapter<ProductsAdapter.ProductsViewHolder>() {

    private var productsFiltered = arrayListOf<Product>()

    class ProductsViewHolder(private var layoutProductsItemBinding: LayoutProductsItemBinding) : RecyclerView.ViewHolder(layoutProductsItemBinding.root) {

        fun bind(product: Product) {
            layoutProductsItemBinding.product = product
            layoutProductsItemBinding.executePendingBindings()
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ProductsViewHolder =
        ProductsViewHolder(DataBindingUtil.inflate(LayoutInflater.from(parent.context), R.layout.layout_products_item, parent,false))

    override fun getItemCount(): Int = productsFiltered.size

    override fun onBindViewHolder(holder: ProductsViewHolder, position: Int) {
        holder.bind(productsFiltered[position])
    }

    fun addUsers(products: List<Product>) {
        this.products.apply {
            clear()
            addAll(products)
        }
        this.productsFiltered.apply {
            clear()
            addAll(products)
        }
    }
}
