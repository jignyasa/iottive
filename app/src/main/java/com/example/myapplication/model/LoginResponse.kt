package com.example.myapplication.model

import com.google.gson.annotations.SerializedName

data class LoginResponse(
    @SerializedName("lastName")
    val lastName: String = "",
    @SerializedName("mobileNumber")
    val mobileNumber: String = "",
    @SerializedName("authToken")
    val authToken: String = "",
    @SerializedName("userId")
    val userId: Int = 0,
    @SerializedName("birthDate")
    val birthDate: String = "",
    @SerializedName("saferPayToken")
    val saferPayToken: String = "",
    @SerializedName("firstName")
    val firstName: String = "",
    @SerializedName("saferPayCardDetails")
    val saferPayCardDetails: String? = null,
    @SerializedName("success")
    val success: Boolean = false,
    @SerializedName("aliasId")
    val aliasId: String = "",
    @SerializedName("rewardPoint")
    val rewardPoint: Int = 0,
    @SerializedName("profileImageURL")
    val profileImageURL: String = "",
    @SerializedName("primaryEmail")
    val primaryEmail: String = "",
    @SerializedName("message")
    val message: String ?= ""
)