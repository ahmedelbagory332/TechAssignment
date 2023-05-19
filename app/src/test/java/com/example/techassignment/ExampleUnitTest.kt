package com.example.techassignment



import org.junit.Test
import org.junit.Assert.*

/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * See [testing documentation](http://d.android.com/tools/testing).
 */
class ExampleUnitTest {

   var repository: FakePreviousOrdersRepository = FakePreviousOrdersRepository()

    @Test
    fun testLoadFirstPage() {
        val pageSize = 10
        var loadedData: List<MyModel>? = null

        repository.loadMoreItems(1, pageSize, object : MyPaginatedList.Callback {
            override fun onLoadSuccess(data: List<MyModel>) {
                loadedData = data
            }

            override fun onLoadFailed(error: Throwable) {
                // Handle error
            }
        })

        assertNotNull(loadedData)
        assertEquals(pageSize, loadedData!!.size)
        assertEquals("Item 1", loadedData!![0].name)
        assertEquals("Item 2", loadedData!![1].name)
        assertEquals("Item 3", loadedData!![2].name)
    }
}