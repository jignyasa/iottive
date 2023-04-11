package com.example.myapplication.model

import com.google.gson.annotations.SerializedName

data class ProductItem(@SerializedName("perProductPrice")
                       val perProductPrice: Double? = 0.0,
                       @SerializedName("productId")
                       val productId: Int? = 0,
                       @SerializedName("productLocalName")
                       val productLocalName: String? = "",
                       @SerializedName("productCount")
                       val productCount: Int? = 0,
                       @SerializedName("productName")
                       val productName: String? = "",
                       @SerializedName("productOtherUrl")
                       val productOtherUrl: String? = "")