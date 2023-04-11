package com.example.myapplication.activity

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.lifecycle.ViewModelProvider
import com.example.myapplication.R
import com.example.myapplication.viewmodel.MainActivityViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {
    private lateinit var viewModel: MainActivityViewModel
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        viewModel=ViewModelProvider(this).get(MainActivityViewModel::class.java)
        viewModel.getUserData(this)
        bubbleSort()
    }

    fun bubbleSort(){
        var arr=arrayOf(2,5,3,6,8,9,4)
        for(i in 0..arr.size-2){
            for(j in 0..arr.size-i-2){
                if(arr[j]>arr[j+1]){
                    var temp=arr[j]
                    arr[j]=arr[j+1]
                    arr[j+1]=temp
                }
            }
        }
        for(i in 0..arr.size-1){
            print(arr[i])
        }
    }

}