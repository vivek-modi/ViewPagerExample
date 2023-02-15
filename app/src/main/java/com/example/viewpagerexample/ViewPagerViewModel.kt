package com.example.viewpagerexample

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.PagingData
import androidx.paging.cachedIn
import kotlinx.coroutines.flow.Flow
import java.util.*

class ViewPagerViewModel(app: Application) : AndroidViewModel(app) {

    // add few days as current date
    private val fewDaysLimitDataSource = DataSource(size = 5, currentDate(), limitDate())

    fun createRepositoryData(repository: ViewPagerRepository): Flow<PagingData<Date>> {
        return repository.getResultStream(limitDate(), limitDate()).cachedIn(viewModelScope)
    }

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