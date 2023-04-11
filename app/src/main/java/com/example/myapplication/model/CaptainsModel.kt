package com.example.myapplication.model

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

data class CaptainsModel(
    @Expose @SerializedName("name") val name: String,
    @Expose @SerializedName("realname") val realname: String,
    @Expose @SerializedName("team") val team: String,
    @Expose @SerializedName("firstappearance") val firstappearance: String,
    @Expose @SerializedName("createdby") val createdby: String,
    @Expose @SerializedName("publisher") val publisher: String,
    @Expose @SerializedName("imageurl") val imageurl: String,
    @Expose @SerializedName("bio") val bio: String
    )
