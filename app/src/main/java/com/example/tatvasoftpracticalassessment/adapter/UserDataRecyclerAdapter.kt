package com.example.tatvasoftpracticalassessment.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.tatvasoftpracticalassessment.R
import com.example.tatvasoftpracticalassessment.models.Users
import com.example.tatvasoftpracticalassessment.viewholder.UserDataViewHolder
import kotlinx.android.synthetic.main.usersdatalistitem.view.*

class PostRecyclerAdapter(private val context: Context, private val userDataList : ArrayList<Users>) :
    RecyclerView.Adapter<UserDataViewHolder>() {
    private var isLoaderVisible = false
    private var mUsersDataItemsList = ArrayList<Users>()
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): UserDataViewHolder {
        return when (viewType) {
            VIEW_TYPE_NORMAL -> ViewHolder(
                LayoutInflater.from(parent.context).inflate(R.layout.usersdatalistitem, parent, false)
            )
            VIEW_TYPE_LOADING -> ProgressHolder(
                LayoutInflater.from(parent.context).inflate(R.layout.item_loading, parent, false)
            )
            else -> ViewHolder(
                LayoutInflater.from(parent.context).inflate(R.layout.usersdatalistitem, parent, false)
            )
        }
    }

    override fun onBindViewHolder(holder: UserDataViewHolder, position: Int) {
        holder.onBind(position)
    }

    override fun getItemViewType(position: Int): Int {
        return if (isLoaderVisible) {
            if (position == userDataList!!.size - 1) VIEW_TYPE_LOADING else VIEW_TYPE_NORMAL
        } else {
            VIEW_TYPE_NORMAL
        }
    }

    override fun getItemCount(): Int {
        return mUsersDataItemsList?.size ?: 0
    }

    fun addItems(userItems: List<Users>?) {
        mUsersDataItemsList!!.addAll(userDataList!!)
        notifyDataSetChanged()
    }

    fun addLoading() {
        isLoaderVisible = true
        mUsersDataItemsList!!.add(Users())
        notifyItemInserted(mUsersDataItemsList.size - 1)
    }

    fun removeLoading() {
        isLoaderVisible = false
        val position = mUsersDataItemsList!!.size - 1
        val item: Users = getItem(position)
        if (item != null) {
            mUsersDataItemsList.removeAt(position)
            notifyItemRemoved(position)
        }
    }

    fun clear() {
        mUsersDataItemsList!!.clear()
        notifyDataSetChanged()
    }

    fun getItem(position: Int): Users {
        return mUsersDataItemsList!![position]
    }

    inner class ViewHolder internal constructor(itemView: View?) : UserDataViewHolder(itemView) {
        protected override fun clear() {}
        override fun onBind(position: Int) {
            super.onBind(position)
            val item: Users = mUsersDataItemsList!![position]
            Glide.with(context).load(item.image).into(itemView!!.userImage)
        }
    }

    inner class ProgressHolder internal constructor(itemView: View?) : UserDataViewHolder(itemView) {
        protected override fun clear() {}
    }

    companion object {
        private const val VIEW_TYPE_LOADING = 0
        private const val VIEW_TYPE_NORMAL = 1
    }

    init {
        mUsersDataItemsList = userDataList
    }
}