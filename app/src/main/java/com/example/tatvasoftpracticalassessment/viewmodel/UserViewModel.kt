package com.example.tatvasoftpracticalassessment.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import com.example.tatvasoftpracticalassessment.models.UserDataResponse
import com.example.tatvasoftpracticalassessment.repository.UserRepository

class UserViewModel(application: Application) : AndroidViewModel(application) {
    private var userRepository: UserRepository? = null
    private var volumesResponseLiveData: LiveData<UserDataResponse?>? = null
    fun init() {
        userRepository = UserRepository()
        volumesResponseLiveData = userRepository!!.getVolumesResponseLiveData()
    }

    fun userVolumes(offset:Int?, limit:Int?) {
        userRepository!!.userVolumes(offset, limit)
    }

    fun getVolumesResponseLiveData(): LiveData<UserDataResponse?>? {
        return volumesResponseLiveData
    }
}