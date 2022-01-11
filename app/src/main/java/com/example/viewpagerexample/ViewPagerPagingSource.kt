package com.example.viewpagerexample

import android.util.Log
import androidx.paging.PagingSource
import androidx.paging.PagingState
import java.io.IOException
import java.text.SimpleDateFormat
import java.util.*

class ViewPagerPagingSource(
    private val dataSource: DataSource
) : PagingSource<Int, Date>() {

    private val format = SimpleDateFormat("dd/MM/yyyy", Locale.getDefault())

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, Date> {
        val position = params.key ?: 0

        return try {
            val data = dataSource.returnData(position)
            Log.e("Page number", "$position ::-> ${data.result.map(format::format)}")
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