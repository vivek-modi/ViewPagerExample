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
        Log.e("startingDate", "" + startingDate)

        tempCalendar.time = startingDate
        val endingDate = endingDate(startingDate)

        Log.e("endingDate", "" + endingDate)

        while (tempCalendar.time < endingDate) {
            dates.add(tempCalendar.time)
            tempCalendar.add(Calendar.DATE, 1)
        }
        return dates
    }

    private fun startingDate(pageNumber: Int): Date {
        Calendar.getInstance().let {
            it.time = currentDate
            it.add(Calendar.DATE, pageNumber * pageSize)
            return it.time
        }
    }

    private fun endingDate(startingDate: Date): Date {
        Calendar.getInstance().let {
            it.time = startingDate
            it.add(Calendar.DATE, pageSize)
            return it.time
        }
    }
}