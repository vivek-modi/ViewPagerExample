package com.example.viewpagerexample

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.paging.Pager
import androidx.paging.PagingConfig
import java.util.*

class ViewPagerViewModel(app: Application) : AndroidViewModel(app) {

    private val dataSource = ViewPagerDataSource(10, currentDate())

    val dataList =
        Pager(config = PagingConfig(
            pageSize = 10,
            enablePlaceholders = true
        ), pagingSourceFactory = {
            ViewPagerPagingSource(dataSource)
        }).flow

    private fun currentDate(): Date {
        val calendar = Calendar.getInstance()
        return calendar.time
    }
}