package com.example.viewpagerexample

import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import java.util.*

class ViewPagerViewHolder(parent: ViewGroup) : RecyclerView.ViewHolder(
    LayoutInflater.from(parent.context).inflate(R.layout.date_item, parent, false)
) {
    private val dateTextView = itemView.findViewById<TextView>(R.id.date_text_view)

    fun bindTo(item: Date?) {
        dateTextView.text = item.toString()
    }
}