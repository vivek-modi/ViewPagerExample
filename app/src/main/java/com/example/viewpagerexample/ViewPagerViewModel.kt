package com.example.viewpagerexample

import android.app.Application
import android.util.Log
import androidx.lifecycle.AndroidViewModel
import androidx.paging.Pager
import androidx.paging.PagingConfig
import java.text.SimpleDateFormat
import java.util.*

class ViewPagerViewModel(app: Application) : AndroidViewModel(app) {

    val format = SimpleDateFormat("dd/MM/yyyy")

    private val dataSource = DataSource(size = 5, currentDate(), currentDate())

    val dataList =
        Pager(config = PagingConfig(
            pageSize = 5,
            enablePlaceholders = true
        ), pagingSourceFactory = {
            ViewPagerPagingSource(dataSource)
        }).flow

    fun printData() {
        Log.e("Page -1 ", "" + dataSource.returnData(-1).map(format::format))
        Log.e("Page 0 ", "" + dataSource.returnData(0).map(format::format))
        Log.e("Page 1 ", "" + dataSource.returnData(1).map(format::format))
    }

    private fun currentDate(): Date {
        val calendar = Calendar.getInstance()
        return calendar.time
    }
}