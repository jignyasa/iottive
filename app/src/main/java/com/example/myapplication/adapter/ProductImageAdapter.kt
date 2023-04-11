package com.example.myapplication.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.widget.AppCompatImageView
import androidx.appcompat.widget.AppCompatTextView
import androidx.databinding.ObservableArrayList
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.bitmap.RoundedCorners
import com.example.myapplication.R
import com.example.myapplication.model.DataItem
import com.example.myapplication.model.ProductItem

class ProductImageAdapter(var listData:ObservableArrayList<ProductItem>) : RecyclerView.Adapter<ProductImageViewHolder>() {
    var list=ArrayList<ProductItem?>()
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ProductImageViewHolder {
        val view=LayoutInflater.from(parent.context).inflate(R.layout.item_product_images,parent,false)
        return ProductImageViewHolder(view)
    }

    override fun onBindViewHolder(holder: ProductImageViewHolder, position: Int) {
        val data=listData.get(position)
        Glide.with(holder.itemView.context).load(data.productOtherUrl).transform(RoundedCorners(10)).into(holder.ivProduct)
    }

    fun addData(list:ArrayList<ProductItem?>)
    {
       this.list=list
        notifyDataSetChanged()
    }

    override fun getItemCount(): Int {
        return listData.size
    }
}

class ProductImageViewHolder(viewHolder: View) :RecyclerView.ViewHolder(viewHolder) {
    val ivProduct=viewHolder.findViewById<AppCompatImageView>(R.id.ivproduct)
}
