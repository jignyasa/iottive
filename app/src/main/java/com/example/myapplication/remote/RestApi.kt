package com.example.myapplication.remote

import com.example.myapplication.model.CaptainsModel
import com.example.myapplication.model.LoginResponse
import com.example.myapplication.model.ProductResponse
import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName
import io.reactivex.Observable
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Query

interface RestApi {
    @GET("marvel")
    suspend fun getData(): Response<List<CaptainsModel>>

    @POST("loginV1")
    fun login(
        @Query("phoneManufecture") name: String,
        @Query("password") password: String,
        @Query("phoneModel") phoneModel: String,
        @Query("OSVersion") osVersion: Double,
        @Query("languageCode") languageCode: String,
        @Query("email") email: String,
        @Query("platform") platform: String
    ):Observable<LoginResponse>

    @POST("orderHistory")
    fun productInfo(
        @Query("authToken") authToken: String,
        @Query("userId") userId: Int
    ):Observable<ProductResponse>
}