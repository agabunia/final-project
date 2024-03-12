package com.example.final_project.presentation.adapter.home

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.final_project.databinding.CategoryWrapperLayoutBinding
import com.example.final_project.presentation.adapter.common_product_adapter.ProductRecyclerAdapter
import com.example.final_project.presentation.model.home.CategoryList

class WrapperRecyclerAdapter :
    ListAdapter<CategoryList, WrapperRecyclerAdapter.WrapperViewHolder>(CategoryDiffUtil()) {

    class CategoryDiffUtil : DiffUtil.ItemCallback<CategoryList>() {
        override fun areItemsTheSame(oldItem: CategoryList, newItem: CategoryList): Boolean {
            return oldItem.categoryName == newItem.categoryName
        }

        override fun areContentsTheSame(oldItem: CategoryList, newItem: CategoryList): Boolean {
            return oldItem == newItem
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): WrapperViewHolder {
        val inflate = LayoutInflater.from(parent.context)
        return WrapperViewHolder(CategoryWrapperLayoutBinding.inflate(inflate, parent, false))
    }

    override fun onBindViewHolder(holder: WrapperViewHolder, position: Int) {
        holder.bind()
    }

    var onWrapperItemClick: ((Int) -> Unit)? = null

    inner class WrapperViewHolder(private val binding: CategoryWrapperLayoutBinding) :
        RecyclerView.ViewHolder(binding.root) {
        private lateinit var category: CategoryList
        private val productRecyclerAdapter = ProductRecyclerAdapter()

        init {
            binding.rvProductList.apply {
                layoutManager = LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false)
                adapter = productRecyclerAdapter
            }
        }

        fun bind() {
            category = currentList[adapterPosition]

            binding.apply {
                tvCategoryTitle.text = category.categoryName
                productRecyclerAdapter.submitList(category.productList.products)
                productRecyclerAdapter.onItemClick = {
                    onWrapperItemClick?.invoke(it)
                }
            }
        }

    }
}