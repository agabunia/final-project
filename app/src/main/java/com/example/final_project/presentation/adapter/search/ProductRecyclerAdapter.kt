package com.example.final_project.presentation.adapter.search

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.final_project.databinding.ProductLayoutBinding
import com.example.final_project.presentation.model.search.Product

class ProductRecyclerAdapter : ListAdapter<Product, ProductRecyclerAdapter.ProductViewHolder>(ProductDiffUtil()) {

    class ProductDiffUtil : DiffUtil.ItemCallback<Product>() {
        override fun areItemsTheSame(oldItem: Product, newItem: Product): Boolean {
            return oldItem.id == newItem.id
        }

        override fun areContentsTheSame(oldItem: Product, newItem: Product): Boolean {
            return oldItem == newItem
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ProductViewHolder {
        val inflate = LayoutInflater.from(parent.context)
        return ProductViewHolder(ProductLayoutBinding.inflate(inflate, parent, false))
    }

    override fun onBindViewHolder(holder: ProductViewHolder, position: Int) {
        holder.bind()
    }

    var onItemClick: ((Int) -> Unit)? = null

    inner class ProductViewHolder(private val binding: ProductLayoutBinding) : RecyclerView.ViewHolder(binding.root) {

        fun bind() {}
    }

}