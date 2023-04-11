package com.example.myapplication.viewmodel

import android.content.Context
import android.os.Build
import android.util.Log
import android.widget.Toast
import androidx.databinding.ObservableArrayList
import androidx.databinding.ObservableBoolean
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModel
import com.example.myapplication.R
import com.example.myapplication.Utility.Constants
import com.example.myapplication.Utility.MyPreferences
import com.example.myapplication.Utility.Utility
import com.example.myapplication.model.DataItem
import com.example.myapplication.model.LoginResponse
import com.example.myapplication.model.ProductResponse
import com.example.myapplication.model.request.LoginRequest
import com.example.myapplication.remote.Respository
import dagger.hilt.android.lifecycle.HiltViewModel
import io.reactivex.Scheduler
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.Disposable
import io.reactivex.schedulers.Schedulers
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(val repository: Respository,
                                        val context: Context,
                                        val preferences: MyPreferences
) : ViewModel() {
    var _data = MutableLiveData<Pair<Any, Any>>()
    var productList=ObservableArrayList<DataItem>()
    var isLoading=ObservableBoolean(false)
    fun mapEvent(): MutableLiveData<Pair<Any, Any>> {
        return _data
    }

    fun productDetail() {
        if (Utility.isConnected(context)) {
            isLoading.set(true)
            repository.productInfo(
            ).subscribeOn(Schedulers.io())?.observeOn(AndroidSchedulers.mainThread())
                ?.subscribe(object : io.reactivex.Observer<ProductResponse> {
                    override fun onSubscribe(d: Disposable) {

                    }

                    override fun onNext(t: ProductResponse) {
                        isLoading.set(false)
                        if (t.success) {
                            Log.e("maa", "success".plus(t.data))
                            if(productList.isEmpty().not())
                                productList.clear()

                            t.data?.let { productList.addAll(it) }
                            _data.postValue(Pair(Constants.SUCCESS, t.message.toString()))
                        } else {
                            _data.postValue(Pair(Constants.ERROR, t.message.toString()))
                        }
                    }

                    override fun onError(e: Throwable) {
                        isLoading.set(false)
                        Log.e("error", e.message.toString())
                    }

                    override fun onComplete() {

                    }

                })

        } else {
            _data.postValue(Pair(Constants.ERROR, context.getString(R.string.noInternetAvailable)))
        }
    }
}