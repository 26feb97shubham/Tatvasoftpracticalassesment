package com.example.tatvasoftpracticalassessment.repository

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.tatvasoftpracticalassessment.models.UserDataResponse
import com.example.tatvasoftpracticalassessment.rest.UserDataService
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory


class UserRepository {
    private val BOOK_SEARCH_SERVICE_BASE_URL = "http://sd2-hiring.herokuapp.com/"

    private var userDataService: UserDataService? = null
    private var userDataResponseLiveData: MutableLiveData<UserDataResponse?>? = null

    fun userRepository() {
        userDataResponseLiveData = MutableLiveData<UserDataResponse?>()
        val interceptor = HttpLoggingInterceptor()
        interceptor.level = HttpLoggingInterceptor.Level.BODY
        val client = OkHttpClient.Builder().addInterceptor(interceptor).build()
        userDataService = Retrofit.Builder()
            .baseUrl(BOOK_SEARCH_SERVICE_BASE_URL)
            .client(client)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(UserDataService::class.java)
    }

    fun userVolumes(offset:Int?, limit:Int?) {
        userDataService!!.doGetUserList(offset, limit)!!.enqueue(object : Callback<UserDataResponse?> {
                override fun onResponse(
                    call: Call<UserDataResponse?>?,
                    response: Response<UserDataResponse?>
                ) {
                    if (response.body() != null) {
                        userDataResponseLiveData!!.postValue(response.body())
                    }
                }

                override fun onFailure(call: Call<UserDataResponse?>?, t: Throwable?) {
                    userDataResponseLiveData!!.postValue(null)
                }
            })
    }

    fun getVolumesResponseLiveData(): LiveData<UserDataResponse?>? {
        return userDataResponseLiveData
    }

}