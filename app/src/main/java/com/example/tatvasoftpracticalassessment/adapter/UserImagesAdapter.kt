package com.example.tatvasoftpracticalassessment.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.tatvasoftpracticalassessment.R
import kotlinx.android.synthetic.main.userdataimagesitem.view.*

class UserImagesAdapter(private val context : Context, private val userImages : ArrayList<String>) :
    RecyclerView.Adapter<UserImagesAdapter.UserImagesAdapterVH>() {
    inner class UserImagesAdapterVH(private val itemView : View) : RecyclerView.ViewHolder(itemView) {
        fun bind(userDataImage: String) {
            Glide.with(context).load(userDataImage).into(itemView!!.userDataImageView)
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): UserImagesAdapterVH {
        val mView = LayoutInflater.from(context).inflate(R.layout.userdataimagesitem,parent, false)
        return UserImagesAdapterVH(mView)
    }

    override fun onBindViewHolder(holder: UserImagesAdapterVH, position: Int) {
        val userDataImage = userImages[position]
        holder.bind(userDataImage)
    }

    override fun getItemCount(): Int {
        return userImages.size
    }
}