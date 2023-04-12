package com.example.myapplication.activity

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.util.Patterns
import android.widget.Toast
import androidx.appcompat.widget.SearchView
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.myapplication.R
import com.example.myapplication.Utility.Constants
import com.example.myapplication.Utility.MyPreferences
import com.example.myapplication.Utility.Utility
import com.example.myapplication.adapter.ProductAdapter
import com.example.myapplication.databinding.ActivityHomeBinding
import com.example.myapplication.databinding.ActivityLoginBinding
import com.example.myapplication.listener.OnImageClickListener
import com.example.myapplication.model.ProductItem
import com.example.myapplication.viewmodel.HomeViewModel
import com.example.myapplication.viewmodel.LoginViewModel
import com.example.myapplication.viewmodel.MainActivityViewModel
import dagger.hilt.android.AndroidEntryPoint
import java.util.regex.Matcher
import javax.inject.Inject

@AndroidEntryPoint
class HomeActivity : AppCompatActivity() ,OnImageClickListener{
    lateinit var binding:ActivityHomeBinding
    private lateinit var viewModel: HomeViewModel
    lateinit var adapter: ProductAdapter
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        initViewModel()
        initView()
        subsribeObserver()
    }

    private fun subsribeObserver() {
        viewModel.mapEvent().observe(this, Observer {
            if(it.first.equals(Constants.ERROR)){
                Toast.makeText(this@HomeActivity,it.second.toString(),Toast.LENGTH_SHORT).show()
            }else if(it.first.equals(Constants.SUCCESS)){
                adapter.addData(viewModel.productList)
                adapter.notifyDataSetChanged()
            }
        })
    }

    private fun initViewModel() {
        viewModel=ViewModelProvider(this).get(HomeViewModel::class.java)
        viewModel.productDetail()
    }

    private fun initView() {
        binding=DataBindingUtil.setContentView(this,R.layout.activity_home)
        binding.viewModel=viewModel
        adapter = ProductAdapter(this)
        binding.rvOrders.adapter = adapter
        binding.rvOrders.layoutManager = LinearLayoutManager(this)
        binding.searchView.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(query: String?): Boolean {
                return false
            }

            override fun onQueryTextChange(newText: String?): Boolean {
                adapter.filter.filter(newText)
                return false
            }
        })
    }

    override fun onImageClick(data: ProductItem) {
        val intent=Intent(this@HomeActivity,ProductDetailActivity::class.java)
            intent.putExtra(Constants.DATA,data as ProductItem)
        startActivity(intent)
    }
}