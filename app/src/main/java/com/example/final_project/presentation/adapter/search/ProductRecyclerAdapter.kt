package com.example.final_project.presentation.adapter.search

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.final_project.databinding.ProductLayoutBinding
import com.example.final_project.presentation.extention.loadImage
import com.example.final_project.presentation.model.search.Products

class ProductRecyclerAdapter : ListAdapter<Products.ProductDetailed, ProductRecyclerAdapter.ProductViewHolder>(ProductDiffUtil()) {

    class ProductDiffUtil : DiffUtil.ItemCallback<Products.ProductDetailed>() {
        override fun areItemsTheSame(oldItem: Products.ProductDetailed, newItem: Products.ProductDetailed): Boolean {
            return oldItem.id == newItem.id
        }

        override fun areContentsTheSame(oldItem: Products.ProductDetailed, newItem: Products.ProductDetailed): Boolean {
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
        private lateinit var product: Products.ProductDetailed

        fun bind() {
            product = currentList[adapterPosition]

            binding.apply {
                sivProductImage.loadImage(product.thumbnail)
                tvProductTitle.text = product.title
                tvProductPrice.text = product.price.toString()
            }
        }
    }

}