package com.example.viewpagerexample

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.*
import kotlinx.coroutines.flow.Flow
import java.util.*

class ViewPagerViewModel(app: Application) : AndroidViewModel(app) {

    // add few days as current date
    private val fewDaysLimitDataSource = DataSource(size = 5, currentDate(), limitDate())
    private val pagingSourceFactory = InvalidatingPagingSourceFactory {
        val currentDateLimitDataSource = DataSource(size = 5, limitDate(), limitDate())
        ViewPagerPagingSource(currentDateLimitDataSource)
    }
    val dataList: Flow<PagingData<Date>> = Pager(
        config = PagingConfig(
            pageSize = 1
        ), pagingSourceFactory = pagingSourceFactory
    ).flow.cachedIn(viewModelScope)

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