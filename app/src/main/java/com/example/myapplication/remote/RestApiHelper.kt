package com.example.myapplication.remote

import com.example.myapplication.model.CaptainsModel
import com.example.myapplication.model.LoginResponse
import com.example.myapplication.model.ProductResponse
import io.reactivex.Observable
import retrofit2.Response
import retrofit2.Retrofit
import javax.inject.Inject

class RestApiHelper @Inject constructor(val retrofit: Retrofit) : RestApi {
    private val api = retrofit.create(RestApi::class.java)
    override suspend fun getData(): Response<List<CaptainsModel>> {
        return api.getData()
    }

    override fun login(
        name: String,
        password: String,
        phoneModel: String,
        osVersion: Double,
        languageCode: String,
        email: String,
        platform: String
    ): Observable<LoginResponse> {
        return  api.login(name, password, phoneModel, osVersion, languageCode, email, platform)
    }

    override fun productInfo(authToken: String, userId: Int): Observable<ProductResponse> {
        return api.productInfo(authToken, userId)
    }
}