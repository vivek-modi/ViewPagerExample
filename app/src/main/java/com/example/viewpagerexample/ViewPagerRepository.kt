package com.example.viewpagerexample

import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import kotlinx.coroutines.flow.Flow
import java.util.*

class ViewPagerRepository {

    fun getResultStream(
        startDate: Date,
        endDate: Date,
    ): Flow<PagingData<Date>> {

        val currentDateLimitDataSource = DataSource(size = 5, startDate, endDate)

        return Pager(config = PagingConfig(
            pageSize = 1
        ), pagingSourceFactory = {
            ViewPagerPagingSource(currentDateLimitDataSource)
        }).flow
    }
}