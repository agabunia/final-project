//package com.example.final_project.presentation.adapter.home
//
//import android.content.Context
//import android.view.LayoutInflater
//import android.view.View
//import android.view.ViewGroup
//import androidx.recyclerview.widget.DiffUtil
//import androidx.recyclerview.widget.LinearLayoutManager
//import androidx.recyclerview.widget.ListAdapter
//import androidx.recyclerview.widget.RecyclerView
//import com.example.final_project.R
//import com.example.final_project.databinding.HomeFragmentImageLayoutBinding
//import com.example.final_project.databinding.HomeFragmentRecyclerLayoutBinding
//import com.example.final_project.presentation.extention.loadImage
//import com.example.final_project.presentation.model.common_product_list.Products
//import com.example.final_project.presentation.model.home.CategoryWrapperList
//import com.example.final_project.presentation.model.home.DataItemTypes
//import com.example.final_project.presentation.model.home.HomeModel
//import com.example.final_project.presentation.model.home.ImageModel
//
//
//class MainRecyclerAdapter(private val items: List<HomeModel>) :
//    RecyclerView.Adapter<RecyclerView.ViewHolder>() {
//
//    class HomeDiffUtil: DiffUtil.ItemCallback<HomeModel>() {
//        override fun areItemsTheSame(oldItem: HomeModel, newItem: HomeModel): Boolean {
//            return oldItem == newItem
//        }
//
//        override fun areContentsTheSame(oldItem: HomeModel, newItem: HomeModel): Boolean {
//            return oldItem == newItem
//        }
//    }
//
//    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
//        return when (viewType) {
//            DataItemTypes.BANNER -> {
//                val binding = HomeFragmentImageLayoutBinding.inflate(
//                    LayoutInflater.from(parent.context),
//                    parent,
//                    false
//                )
//                BannerViewHolder(binding)
//            }
//
//            DataItemTypes.CATEGORY_LIST -> {
//                val binding = HomeFragmentRecyclerLayoutBinding.inflate(
//                    LayoutInflater.from(parent.context),
//                    parent,
//                    false
//                )
//                CategoryRecyclerViewHolder(binding)
//            }
//
//            else -> throw IllegalArgumentException("Invalid view type")
//        }
//    }
//
//    override fun getItemCount(): Int {
//        return items.size
//    }
//
//    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
//        val item = items[position]
//        when (holder) {
//            is BannerViewHolder -> holder.bindBanner(item.image!!)
//            is CategoryRecyclerViewHolder -> holder.bindCategoryRecycler(item.categoryWrapper!!)
//        }
//    }
//
//    override fun getItemViewType(position: Int): Int {
//        return items[position].viewType
//    }
//
//    inner class BannerViewHolder(private val binding: HomeFragmentImageLayoutBinding) :
//        RecyclerView.ViewHolder(binding.root) {
//        fun bindBanner(image: String) {
//            binding.sivProductMarketing.loadImage(image)
//        }
//    }
//
//    var onMainAdapterClick: ((Int) -> Unit)? = null
//    var onMainAdapterSaveClick: ((Products.ProductDetailed) -> Unit)? = null
//
//    inner class CategoryRecyclerViewHolder(private val binding: HomeFragmentRecyclerLayoutBinding) :
//        RecyclerView.ViewHolder(binding.root) {
//        private lateinit var wrapperRecyclerAdapter: WrapperRecyclerAdapter
//
//        fun bindCategoryRecycler(recyclerItem: List<CategoryWrapperList>) {
//            wrapperRecyclerAdapter = WrapperRecyclerAdapter()
//            wrapperRecyclerAdapter.onWrapperItemClick = {
//                onMainAdapterClick?.invoke(it)
//            }
//            wrapperRecyclerAdapter.onWrapperSaveProductClick = {
//                onMainAdapterSaveClick?.invoke(it)
//            }
//            binding.apply {
//                rvWrapper.layoutManager = LinearLayoutManager(binding.root.context)
//                rvWrapper.setHasFixedSize(true)
//                rvWrapper.adapter = wrapperRecyclerAdapter
//            }
//            wrapperRecyclerAdapter.submitList(recyclerItem)
//        }
//    }
//
//}
