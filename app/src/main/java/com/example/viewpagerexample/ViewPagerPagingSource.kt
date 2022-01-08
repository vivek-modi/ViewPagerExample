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
                data = data.result,
                prevKey = data.prevKey,
                nextKey = data.nextKey
            )
        } catch (exception: IOException) {
            LoadResult.Error(exception)
        }
    }

    override fun getRefreshKey(state: PagingState<Int, Date>): Int? {
        return state.anchorPosition?.let { anchorPosition ->
            val anchorPage = state.closestPageToPosition(anchorPosition)
            anchorPage?.prevKey?.plus(1) ?: anchorPage?.nextKey?.minus(1)
        }
    }
}