package com.bcit.miniapp.data

import com.google.gson.annotations.SerializedName

data class Fact (
    @SerializedName("data")
    val factList: List<String>
)

data class Image (
    val id:String,
    val url:String,
    val width:Int,
    val height:Int
)