package com.example.viewpagerexample

import java.util.*

class DataSource(
    private val size: Int = 5,
    private val currentDate: Date,
    private val limitDate: Date?
) {

    fun returnData(pageNumber: Int): List<Date> {

        val dateList = mutableListOf<Date>()
        val startDateForPage = startDate(pageNumber)
        val tempCalendar = Calendar.getInstance()

        tempCalendar.time = startDateForPage
        val lastDateForPage = endDate(startDateForPage)

        while (tempCalendar.time < lastDateForPage) {
            if (limitDate == null ||
                tempCalendar.time.before(limitDate) ||
                tempCalendar.time == limitDate
            ) {
                dateList.add(tempCalendar.time)
                tempCalendar.add(Calendar.DATE, 1)
            } else {
                break
            }
        }
        return dateList
    }

    private fun startDate(pageNumber: Int): Date {
        if (pageNumber == 0) {
            return currentDate
        } else {
            Calendar.getInstance().let {
                it.time = currentDate
                it.add(Calendar.DATE, pageNumber * size)
                return it.time
            }
        }
    }

    private fun endDate(firstDateForPage: Date): Date {
        Calendar.getInstance().let {
            it.time = firstDateForPage
            it.add(Calendar.DATE, size)
            return it.time
        }
    }
}