package com.example.techassignment




data class MyModel(val id: String, val name: String)

class FakePreviousOrdersRepository (): MyPaginatedList {

    private val data = listOf(
        MyModel("1", "Item 1"),
        MyModel("2", "Item 2"),
        MyModel("3", "Item 3")
    )
    override fun loadMoreItems(page: Int, pageSize: Int, callback: MyPaginatedList.Callback) {
        callback.onLoadSuccess(data)
    }
}