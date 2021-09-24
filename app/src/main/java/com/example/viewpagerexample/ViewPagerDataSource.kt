package com.example.viewpagerexample

import android.util.Log
import java.util.*

class ViewPagerDataSource(
    private val pageSize: Int,
    private val currentDate: Date
) {
    fun loadDataSource(pageNumber: Int): List<Date> {
        val dates = mutableListOf<Date>()
        val startingDate = startingDate(pageNumber)
        val tempCalendar = Calendar.getInstance()
        tempCalendar.time = startingDate

        Log.e("loadData startingDate", "" + startingDate)

        var index = 0;
        while (index++ < pageSize) {
            dates.add(tempCalendar.time)
            tempCalendar.add(Calendar.DATE, 1)
        }
        return dates
    }

    private fun startingDate(pageNumber: Int): Date {
        Calendar.getInstance().let {
            it.time = currentDate
            it.add(Calendar.DATE, (pageNumber * pageSize) + pageSize)
            return it.time
        }
    }
}