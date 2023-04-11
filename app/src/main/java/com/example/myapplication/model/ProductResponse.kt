package com.example.myapplication.model

import com.google.gson.annotations.SerializedName

data class ProductResponse(@SerializedName("data")
                           val data: List<DataItem>?,
                           @SerializedName("success")
                           val success: Boolean = false,
                           @SerializedName("message")
                           val message: String ?= "")