package com.example.myapplication.model.request

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

data class LoginRequest(
    @Expose @SerializedName("phoneManufecture") val phoneManufecture: String,
    @Expose @SerializedName("password") val password: String,
    @Expose @SerializedName("phoneModel") val phoneModel: String,
    @Expose @SerializedName("OSVersion") val osVersion: Double,
    @Expose @SerializedName("languageCode") val languageCode: String,
    @Expose @SerializedName("email") val email: String,
    @Expose @SerializedName("platform") val platform: String
    )
