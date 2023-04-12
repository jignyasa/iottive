package com.example.myapplication.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.widget.AppCompatImageView
import androidx.appcompat.widget.AppCompatTextView
import androidx.databinding.DataBindingUtil
import androidx.databinding.ObservableArrayList
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.bumptech.glide.load.resource.bitmap.RoundedCorners
import com.bumptech.glide.request.RequestOptions
import com.example.myapplication.R
import com.example.myapplication.databinding.ItemProductImagesBinding
import com.example.myapplication.listener.OnImageClickListener
import com.example.myapplication.model.DataItem
import com.example.myapplication.model.ProductItem

class ProductImageAdapter() : RecyclerView.Adapter<ProductImageViewHolder>() {
//class ProductImageAdapter(var listData:ObservableArrayList<ProductItem>) : RecyclerView.Adapter<ProductImageViewHolder>() {
    var list=ArrayList<ProductItem>()
    lateinit var listener:OnImageClickListener
    lateinit var binding: ItemProductImagesBinding
    lateinit var context: Context
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ProductImageViewHolder {
        context=parent.context
        binding=DataBindingUtil.inflate<ItemProductImagesBinding>(LayoutInflater.from(parent.context),R.layout.item_product_images,parent,false)
        return ProductImageViewHolder(binding.root)
    }

    override fun onBindViewHolder(holder: ProductImageViewHolder, position: Int) {
        val data=list.get(position)
        val option=RequestOptions().skipMemoryCache(true).diskCacheStrategy(DiskCacheStrategy.NONE)
        Glide.with(context).apply { option }.load(data.productOtherUrl).into(binding.ivproduct).clearOnDetach()
        holder.ivProduct.setOnClickListener {  listener.onImageClick(data)}
    }

    fun addData(listData:ArrayList<ProductItem>)
    {
       this.list=listData
        notifyDataSetChanged()
    }

    override fun getItemCount(): Int {
        return list.size
    }

    fun setOnImageClickListener(listenerInstance: OnImageClickListener){
        listener=listenerInstance
    }
}

class ProductImageViewHolder(viewHolder: View) :RecyclerView.ViewHolder(viewHolder) {
    val ivProduct=viewHolder.findViewById<AppCompatImageView>(R.id.ivproduct)
}
