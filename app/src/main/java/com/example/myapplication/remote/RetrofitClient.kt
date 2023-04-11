package com.example.myapplication.remote

import com.example.myapplication.Utility.Constants
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.create

class RetrofitClient {
    private var myApi: RestApi? = null

    init {
        var httpLiginInterceptor = HttpLoggingInterceptor()
        var okhttpClient = OkHttpClient.Builder()
            .addInterceptor(httpLiginInterceptor.setLevel(HttpLoggingInterceptor.Level.BODY))
            .build()
        val retrofit = Retrofit.Builder().client(okhttpClient).baseUrl(Constants.BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
            .build()
        myApi = retrofit.create(RestApi::class.java)
    }

    companion object {
        private var instance:RetrofitClient? = null
        fun getInstance(): RetrofitClient? {
            if (instance == null) {
                instance = RetrofitClient()
            }
            return instance
        }

    }

    fun getMyApi(): RestApi? {
        return myApi
    }
}