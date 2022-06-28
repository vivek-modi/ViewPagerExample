package com.example.viewpagerexample

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import kotlinx.coroutines.flow.Flow
import java.util.*

class ViewPagerViewModel(app: Application) : AndroidViewModel(app) {

    // pass current date as limit date
    private val currentDateLimitDataSource = DataSource(size = 5, limitDate(), limitDate())

    // add few days as current date
    private val fewDaysLimitDataSource = DataSource(size = 5, currentDate(), limitDate())

    val dataList: Flow<PagingData<Date>> = Pager(config = PagingConfig(
        pageSize = 1
    ), pagingSourceFactory = {
        ViewPagerPagingSource(currentDateLimitDataSource)
    }).flow

    private fun currentDate(): Date {
        val calendar = Calendar.getInstance()
        calendar.add(Calendar.DATE, -10)
        return calendar.time
    }

    private fun limitDate(): Date {
        val calendar = Calendar.getInstance()
        return calendar.time
    }
}