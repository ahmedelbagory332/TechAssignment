package com.example.techassignment

interface MyPaginatedList {
    interface Callback {
        fun onLoadSuccess(data: List<MyModel>)
        fun onLoadFailed(error: Throwable)
    }

    fun loadMoreItems(page: Int, pageSize: Int, callback: Callback)
}