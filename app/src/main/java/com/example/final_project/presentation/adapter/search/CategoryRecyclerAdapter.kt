package com.example.final_project.presentation.adapter.search

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.final_project.databinding.CategoryLayoutBinding
import com.example.final_project.presentation.extention.loadImage
import com.example.final_project.presentation.model.search.Category

class CategoryRecyclerAdapter :
    ListAdapter<Category, CategoryRecyclerAdapter.CategoryViewHolder>(CategoryDiffUtil()) {

    class CategoryDiffUtil : DiffUtil.ItemCallback<Category>() {
        override fun areItemsTheSame(oldItem: Category, newItem: Category): Boolean {
            return oldItem.id == newItem.id
        }

        override fun areContentsTheSame(oldItem: Category, newItem: Category): Boolean {
            return oldItem == newItem
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CategoryViewHolder {
        val inflate = LayoutInflater.from(parent.context)
        return CategoryViewHolder(CategoryLayoutBinding.inflate(inflate, parent, false))
    }

    override fun onBindViewHolder(holder: CategoryViewHolder, position: Int) {
        holder.bind()
    }

    var onItemClick: ((Int) -> Unit)? = null

    inner class CategoryViewHolder(private val binding: CategoryLayoutBinding) :
        RecyclerView.ViewHolder(binding.root) {
        private lateinit var category: Category

        fun bind() {
            category = currentList[adapterPosition]
            binding.apply {
                tvCategoryTitle.text = category.name
                sivCategoryImage.loadImage(category.image)
                layoutCategory.setOnClickListener {
                    onItemClick?.invoke(category.id)
                }
            }
        }
    }
}