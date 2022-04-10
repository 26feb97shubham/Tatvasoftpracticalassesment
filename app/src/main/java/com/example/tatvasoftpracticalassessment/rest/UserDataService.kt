package com.example.tatvasoftpracticalassessment.rest

import com.example.tatvasoftpracticalassessment.models.UserDataResponse
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface UserDataService {
    @GET("/api/users?")
    fun doGetUserList(@Query("offset") offset: Int?, @Query("limit") limit: Int?): Call<UserDataResponse?>?
}