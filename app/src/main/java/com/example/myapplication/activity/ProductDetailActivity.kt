package com.example.myapplication.activity

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.bumptech.glide.Glide
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.bumptech.glide.request.RequestOptions
import com.example.myapplication.R
import com.example.myapplication.Utility.Constants
import com.example.myapplication.Utility.MyPreferences
import com.example.myapplication.databinding.ActivityProductDetailBinding
import com.example.myapplication.model.ProductItem
import com.example.myapplication.viewmodel.ProductDetailViewModel
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class ProductDetailActivity : AppCompatActivity() {
    lateinit var binding:ActivityProductDetailBinding
    private lateinit var viewModel: ProductDetailViewModel
    @Inject
    lateinit var preferences: MyPreferences
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        initViewModel()
        initView()
        subsribeObserver()
    }

    private fun subsribeObserver() {
        viewModel.mapEvent().observe(this, Observer {
            if(it.first.equals(Constants.ERROR)){
                Toast.makeText(this@ProductDetailActivity,it.second.toString(),Toast.LENGTH_SHORT).show()
            }else if(it.first.equals(Constants.SUCCESS)){
                startActivity(Intent(this@ProductDetailActivity,HomeActivity::class.java))
                finish()
            }
        })
    }

    private fun initViewModel() {
        viewModel=ViewModelProvider(this).get(ProductDetailViewModel::class.java)
    }

    private fun initView() {
        binding=DataBindingUtil.setContentView(this,R.layout.activity_product_detail)
        if(intent!=null){
            val data=intent.getSerializableExtra(Constants.DATA) as ProductItem
            viewModel.productId.set(data.productId.toString())
            viewModel.productName.set(data.productName.toString())
            viewModel.price.set(data.perProductPrice.toString())
            viewModel.count.set(data.productCount.toString())
            viewModel.image.set(data.productOtherUrl.toString())
            val option= RequestOptions().skipMemoryCache(true).diskCacheStrategy(DiskCacheStrategy.NONE)
            Glide.with(this).apply { option }.load(data.productOtherUrl).placeholder(R.mipmap.ic_launcher).into(binding.ivProduct).clearOnDetach()
        }
        binding.viewModel=viewModel
    }

    override fun onResume() {
        super.onResume()
       // Glide.with(this).load(viewModel.image.get()).transform(RoundedCorners(10)).into(binding.ivProduct)
    }
}