package com.example.myapplication.viewmodel

import android.content.Context
import android.os.Build
import android.util.Log
import android.widget.Toast
import androidx.databinding.ObservableBoolean
import androidx.databinding.ObservableField
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModel
import com.example.myapplication.R
import com.example.myapplication.Utility.Constants
import com.example.myapplication.Utility.MyPreferences
import com.example.myapplication.Utility.Utility
import com.example.myapplication.model.LoginResponse
import com.example.myapplication.model.request.LoginRequest
import com.example.myapplication.remote.Respository
import dagger.hilt.android.lifecycle.HiltViewModel
import io.reactivex.Scheduler
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.Disposable
import io.reactivex.schedulers.Schedulers
import javax.inject.Inject

@HiltViewModel
class ProductDetailViewModel @Inject constructor(val repository: Respository,
                                                 val context: Context,
                                                 val preferences: MyPreferences
) : ViewModel() {
    var _data = MutableLiveData<Pair<Any, Any>>()
    var isLoading= ObservableBoolean(false)
    var productName= ObservableField("")
    var productId= ObservableField("")
    var price= ObservableField("")
    var count= ObservableField("")
    var image= ObservableField("")
    fun mapEvent(): MutableLiveData<Pair<Any, Any>> {
        return _data
    }

    fun postLogin(email: String, password: String) {
        if (Utility.isConnected(context)) {
            isLoading.set(true)
            repository.login(
                LoginRequest(
                    Build.MANUFACTURER,
                    password,
                    Build.MODEL,
                    Build.VERSION.SDK_INT.toDouble(),
                    Constants.LANGUAGECODE,
                    email,
                    Constants.PLATFORM
                )
            ).subscribeOn(Schedulers.io())?.observeOn(AndroidSchedulers.mainThread())
                ?.subscribe(object : io.reactivex.Observer<LoginResponse> {
                    override fun onSubscribe(d: Disposable) {

                    }

                    override fun onNext(t: LoginResponse) {
                        isLoading.set(false)
                        if (t.success) {
                            Log.e("result", "success")
                            preferences.authToken=t.authToken
                            preferences.userId=t.userId
                            preferences.isLoggedIn=true
                            Log.e("maa", preferences.authToken.toString())
                            _data.postValue(Pair(Constants.SUCCESS, t.message.toString()))
                        } else {
                            _data.postValue(Pair(Constants.ERROR, t.message.toString()))
                        }
                    }

                    override fun onError(e: Throwable) {
                        isLoading.set(false)
                        _data.postValue(Pair(Constants.ERROR, e.message.toString()))
                    }

                    override fun onComplete() {

                    }

                })

        } else {
            _data.postValue(Pair(Constants.ERROR, context.getString(R.string.noInternetAvailable)))
        }
    }
}