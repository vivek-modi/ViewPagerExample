package com.example.viewpagerexample

import androidx.paging.PagingSource
import androidx.paging.PagingState
import java.io.IOException
import java.util.*

class ViewPagerPagingSource(
    private val dataSource: DataSource
) : PagingSource<Int, Date>() {

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, Date> {
        val position = params.key ?: 0

        return try {
            val data = dataSource.returnData(position)
            LoadResult.Page(
                data = data,
                prevKey = if (data.isEmpty()) null else position - 1,
                nextKey = if (data.isEmpty()) null else position + 1,
                itemsBefore = LoadResult.Page.COUNT_UNDEFINED,
                itemsAfter = LoadResult.Page.COUNT_UNDEFINED
            )
        } catch (exception: IOException) {
            LoadResult.Error(exception)
        }
    }
}