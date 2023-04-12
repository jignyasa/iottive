package com.example.myapplication.activity

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Patterns
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.example.myapplication.R
import com.example.myapplication.Utility.Constants
import com.example.myapplication.Utility.MyPreferences
import com.example.myapplication.databinding.ActivityLoginBinding
import com.example.myapplication.viewmodel.LoginViewModel
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class LoginActivity : AppCompatActivity() {
    lateinit var binding:ActivityLoginBinding
    private lateinit var viewModel: LoginViewModel
    @Inject
    lateinit var preferences: MyPreferences
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        initView()
        initViewModel()
        subsribeObserver()
    }

    private fun subsribeObserver() {
        viewModel.mapEvent().observe(this, Observer {
            if(it.first.equals(Constants.ERROR)){
                Toast.makeText(this@LoginActivity,it.second.toString(),Toast.LENGTH_SHORT).show()
            }else if(it.first.equals(Constants.SUCCESS)){
                startActivity(Intent(this@LoginActivity,HomeActivity::class.java))
                finish()
            }
        })
    }

    private fun initViewModel() {
        viewModel=ViewModelProvider(this).get(LoginViewModel::class.java)
        binding.viewModel=viewModel
    }

    private fun initView() {
        binding=DataBindingUtil.setContentView(this,R.layout.activity_login)
        if(preferences.isLoggedIn)
        {
            startActivity(Intent(this@LoginActivity,HomeActivity::class.java))
            finish()
        }
        binding.btnLogin.setOnClickListener {
            if(validation()){
                viewModel.postLogin(binding.etEmail.text.toString(),binding.etPassword.text.toString())
            }
        }
    }

    private fun validation():Boolean{
        var isValid=true
        if(binding.etEmail.text.toString().isNullOrEmpty() ){
            isValid=false
            Toast.makeText(this@LoginActivity,"Please enter  email address",Toast.LENGTH_SHORT).show()
        }else if(!Patterns.EMAIL_ADDRESS.matcher(binding.etEmail.text.toString()).matches()){
            isValid=false
            Toast.makeText(this@LoginActivity,"Please enter proper email address",Toast.LENGTH_SHORT).show()
        }else if(binding.etPassword.text.toString().isNullOrEmpty()){
            isValid=false
            Toast.makeText(this@LoginActivity,"Please enter password address",Toast.LENGTH_SHORT).show()
        }else{
            isValid=true
        }
        return isValid
    }
}