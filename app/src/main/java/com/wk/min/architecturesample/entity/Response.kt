package com.wk.min.architecturesample.entity

import com.google.gson.annotations.SerializedName

data class Response(
    @SerializedName("login") var login:String,
    @SerializedName("url") var url:String)