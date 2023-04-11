package com.example.myapplication.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Filter
import android.widget.Filterable
import androidx.appcompat.widget.AppCompatTextView
import androidx.databinding.DataBindingUtil
import androidx.databinding.ObservableArrayList
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.myapplication.R
import com.example.myapplication.databinding.ItemProductBinding
import com.example.myapplication.model.DataItem
import com.example.myapplication.model.ProductItem

class ProductAdapter() :
    RecyclerView.Adapter<ProductViewHolder>(), Filterable {
    var list = ArrayList<DataItem?>()
    var listData= ArrayList<DataItem>()
    var originalListData= ArrayList<DataItem>()
    lateinit var adapter: ProductImageAdapter
    lateinit var binding: ItemProductBinding
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ProductViewHolder {
        binding = DataBindingUtil.inflate<ItemProductBinding>(
            LayoutInflater.from(parent.context),
            R.layout.item_product,
            parent,
            false
        )
        return ProductViewHolder(binding.root)
    }

    override fun onBindViewHolder(holder: ProductViewHolder, position: Int) {
        val dataModel = listData.get(position)
        binding.tvNoProductValue.text = dataModel.product?.size.toString()
        binding.data = dataModel
        var observableList = ObservableArrayList<ProductItem>()
        dataModel.product?.let { observableList.addAll(it) }
        adapter = ProductImageAdapter(observableList)
        binding.rvImages.adapter = adapter
        binding.rvImages.layoutManager =
            LinearLayoutManager(holder.itemView.context, LinearLayoutManager.HORIZONTAL, false)
    }

    fun addData(list: ArrayList<DataItem>) {
        this.listData = list
        this.originalListData = list
        notifyDataSetChanged()
    }

    override fun getItemCount(): Int {
        return listData.size
    }

    override fun getFilter(): Filter {
        return object : Filter() {
            override fun performFiltering(constraint: CharSequence?): FilterResults {
                val filteredList = if (constraint.isNullOrBlank()) {
                    originalListData
                } else {
                    listData.filter {
                        it.orderID?.contains(constraint, true) == true
                    }
                }
                val results = FilterResults()
                results.values = filteredList
                return results
            }

            override fun publishResults(constraint: CharSequence?, results: FilterResults?) {
                listData = results?.values as? ArrayList<DataItem>
                    ?: ArrayList<DataItem>()
                notifyDataSetChanged()
            }
        }
    }
}

class ProductViewHolder(viewHolder: View) : RecyclerView.ViewHolder(viewHolder) {
    val rvImages = viewHolder.findViewById<RecyclerView>(R.id.rvImages)
}
