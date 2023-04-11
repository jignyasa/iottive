package com.example.myapplication.remote

import com.example.myapplication.Utility.MyPreferences
import com.example.myapplication.model.CaptainsModel
import com.example.myapplication.model.request.LoginRequest
import javax.inject.Inject

class Respository @Inject constructor(val myApi:RestApi,val preferences: MyPreferences) {

    suspend fun getData():ArrayList<CaptainsModel>{
        var list=ArrayList<CaptainsModel>()
        var dataList=myApi.getData().let {
            it?.body()
        }
        dataList?.let { list.addAll(it) }
        return list
    }

    fun login(request: LoginRequest)=myApi.login(request.phoneManufecture,request.password,request.phoneModel,request.osVersion,request.languageCode,request.email,request.platform)

    fun productInfo()=myApi.productInfo(preferences.authToken?:"",preferences.userId)
}