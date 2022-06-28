package com.example.viewpagerexample

import java.util.*

class DataSource(
    private val size: Int = 5,
    private val currentDate: Date,
    private val limitDate: Date? = null
) {

    fun returnData(pageNumber: Int): Result {
        val dateList = mutableListOf<Date>()

        val startDateForPage = startDate(pageNumber)
        val tempCalendar = Calendar.getInstance()

        tempCalendar.time = startDateForPage
        val lastDateForPage = endDate(startDateForPage)

        var index = size
        while (tempCalendar.time <= lastDateForPage && index-- > 0) {
            dateList.add(tempCalendar.time)
            tempCalendar.add(Calendar.DATE, 1)
        }
        return Result(
            result = dateList,
            pageNumber - 1,
            if (lastDateForPage == limitDate)
                null
            else
                pageNumber + 1
        )
    }

    private fun startDate(pageNumber: Int): Date {
        Calendar.getInstance().let {
            it.time = currentDate
            it.add(Calendar.DATE, pageNumber * size - size.minus(1))
            return it.time
        }
    }

    private fun endDate(firstDateForPage: Date): Date? {
        Calendar.getInstance().let {
            it.time = firstDateForPage
            it.add(Calendar.DATE, size)

            limitDate?.let { limit ->
                return if (it.time > limit) limit else it.time
            } ?: run {
                return it.time
            }
        }
    }

    data class Result(
        val result: MutableList<Date>,
        val prevKey: Int?,
        val nextKey: Int?
    )
}