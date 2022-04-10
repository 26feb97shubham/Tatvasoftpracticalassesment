package com.example.tatvasoftpracticalassessment.models

import com.google.gson.annotations.SerializedName
import java.io.Serializable

data class UserDataResponse(
    @SerializedName("status"  ) var status  : Boolean? = null,
    @SerializedName("message" ) var message : String?  = null,
    @SerializedName("data"    ) var data    : Data?    = Data()
) : Serializable

data class Users (

    @SerializedName("name"  ) var name  : String?           = null,
    @SerializedName("image" ) var image : String?           = null,
    @SerializedName("items" ) var items : ArrayList<String> = arrayListOf()

): Serializable

data class Data (

    @SerializedName("users"    ) var users   : ArrayList<Users> = arrayListOf(),
    @SerializedName("has_more" ) var hasMore : Boolean?         = null

)
