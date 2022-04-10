package com.example.tatvasoftpracticalassessment

import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout
import com.example.tatvasoftpracticalassessment.adapter.UserDataRecyclerAdapter
import com.example.tatvasoftpracticalassessment.models.UserDataResponse
import com.example.tatvasoftpracticalassessment.models.Users
import com.example.tatvasoftpracticalassessment.paginationListener.PaginationListener
import com.example.tatvasoftpracticalassessment.paginationListener.PaginationListener.Companion.PAGE_START
import com.example.tatvasoftpracticalassessment.viewmodel.UserViewModel
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity(), SwipeRefreshLayout.OnRefreshListener {
    private var currentPage: Int = PAGE_START
    private var mIsLastPage = false
    private var mTotalPage = 10
    private var mIsLoading = false
    var itemCount = 0
    private var mLayoutManager : LinearLayoutManager?=null
    private var userDataRecyclerAdapter : UserDataRecyclerAdapter?=null
    private var userViewModel : UserViewModel?=null
    private var usersList = ArrayList<Users>()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        swipeRefresh.setOnRefreshListener(this)
        recyclerViewUsersData.setHasFixedSize(true)


        mLayoutManager = LinearLayoutManager(this)
        recyclerViewUsersData.layoutManager = mLayoutManager

        userViewModel = ViewModelProviders.of(this)[UserViewModel::class.java]
        userViewModel!!.init()
        doAPICall()

        /**
         * add scroll listener while user reach in bottom load more will call
         */
        recyclerViewUsersData.addOnScrollListener(object : PaginationListener(mLayoutManager!!){
            override fun loadMoreItems() {
                mIsLoading = true
                currentPage++
                doAPICall()
            }

            override val isLastPage: Boolean
                get() = mIsLastPage
            override val isLoading: Boolean
                get() = mIsLoading

        })

    }

    private fun doAPICall() {
        userViewModel!!.getVolumesResponseLiveData()!!.observe(this, object : Observer<UserDataResponse?>{
            override fun onChanged(userDataResponse: UserDataResponse?) {
                if (userDataResponse!=null){
                    if (userDataResponse.status!!)
                    {
                        if (userDataResponse.data!=null)
                        {
                            usersList = userDataResponse.data!!.users
                            setAdapter(usersList)
                        }else{
                            Toast.makeText(this@MainActivity, "No Data Found", Toast.LENGTH_LONG).show()
                        }
                    }else
                    {
                        Toast.makeText(this@MainActivity, "No Data Found", Toast.LENGTH_LONG).show()
                    }
                }else
                {
                    Toast.makeText(this@MainActivity, "No Response", Toast.LENGTH_LONG).show()
                }
            }
        })

    }

    private fun setAdapter(userDataList: ArrayList<Users>) {
        userDataRecyclerAdapter = UserDataRecyclerAdapter(this, userDataList        )
    }

    override fun onRefresh() {

    }
}