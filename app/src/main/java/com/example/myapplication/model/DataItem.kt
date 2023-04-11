package com.example.myapplication.model

import com.google.gson.annotations.SerializedName

data class DataItem(@SerializedName("date")
                    val date: Long? = 0L,
                    @SerializedName("coolerId")
                    val coolerId: String? = "",
                    @SerializedName("product")
                    val product: List<ProductItem>?,
                    @SerializedName("amountDeductedByRewardPoint")
                    val amountDeductedByRewardPoint: Int? = 0,
                    @SerializedName("orderID")
                    val orderID: String? = "",
                    @SerializedName("redeemedRewards")
                    val redeemedRewards: Int? = 0,
                    @SerializedName("paidAmount")
                    val paidAmount: Int? = 0,
                    @SerializedName("paymentStatus")
                    val paymentStatus: String? = "",
                    @SerializedName("amountDeductedByPaymentGateway")
                    val amountDeductedByPaymentGateway: Int? = 0)