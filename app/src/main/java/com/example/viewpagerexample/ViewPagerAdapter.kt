package com.example.viewpagerexample

import android.util.Log
import android.view.ViewGroup
import androidx.paging.PagingDataAdapter
import androidx.recyclerview.widget.DiffUtil
import java.util.*

class ViewPagerAdapter : PagingDataAdapter<Date, ViewPagerViewHolder>(diffCallback) {

    override fun onBindViewHolder(holder: ViewPagerViewHolder, position: Int) {
        holder.bindTo(getItem(position))
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewPagerViewHolder {
        return ViewPagerViewHolder(parent)
    }

    companion object {
        private val diffCallback = object : DiffUtil.ItemCallback<Date>() {
            override fun areItemsTheSame(oldItem: Date, newItem: Date): Boolean =
                oldItem == newItem

            override fun areContentsTheSame(oldItem: Date, newItem: Date): Boolean =
                oldItem == newItem
        }
    }

    fun isItemPresent(position: Int): Boolean {
        var dataPresent = false
        try {
            dataPresent = getItem(position) != null
        } catch (e: Exception) {
            Log.e("Error we get ", "" + e)
        }
        return dataPresent
    }
}
