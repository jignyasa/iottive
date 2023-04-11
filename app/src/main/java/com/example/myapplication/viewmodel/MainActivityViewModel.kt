package com.example.myapplication.viewmodel

import android.content.Context
import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.myapplication.remote.Respository
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class MainActivityViewModel @Inject constructor(val repository: Respository) : ViewModel() {
    var _data = MutableLiveData<Pair<Any, Any>>()

    //private val repository=Respository()
    fun getUserData(context: Context) {
        /*CoroutineScope(Dispatchers.IO).launch{
            val response=repository.getData()
            if(response!=null)
            {
                val db=MyDb.getInstance(context)
                for(i in response){
                    db?.dataDao()?.insert(DataEntity( i.name,i.realname,1,System.currentTimeMillis()))
                }
                Log.e(":data",response.toString())
                getDb(context)
            }
        }*/
    }

    fun getDb(context: Context) {
        /*CoroutineScope(Dispatchers.IO).launch {
            var listData=MyDb.getInstance(context)?.dataDao()?.getAllNotes()
            Log.e("datta maa",listData.toString())
        }*/
    }
}