package com.example.techassignment.ui.previousOrders

import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.example.techassignment.Result


class PreviousOrdersSource(
    var viewModel: PrevoiusOrdersViewModel,
    var repository: PreviousOrdersRepository
) : PagingSource<Int, Result>() {

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, Result> {

        return try {
            val nextPage = params.key ?: 1
            val response = repository.getOrders(
                nextPage
            )

            if (viewModel.checkResponse(response)) {
                val list = response.body()!!.result
                LoadResult.Page(
                    data = list,
                    prevKey = if (nextPage == 1) null else nextPage - 1,
                    nextKey = if (response.body()?.next != null) response.body()!!.currentPage.plus(
                        1
                    ) else null
                )
            } else {
                LoadResult.Page(
                    data = ArrayList(),
                    prevKey = null,
                    nextKey = null
                )
            }


        } catch (e: Exception) {
            LoadResult.Error(e)
        }

    }

    override fun getRefreshKey(state: PagingState<Int, Result>): Int? {
        return null
    }

}


