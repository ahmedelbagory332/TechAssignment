package com.example.techassignment.ui.currentOrders

import android.content.Context
import com.example.techassignment.OrderResponseModel
import com.example.techassignment.base.BaseRepository
import com.example.techassignment.data.retrofit.ApiServices
import com.example.techassignment.data.shared.DataManager
import retrofit2.Response
import javax.inject.Inject

class CurrentOrdersRepository @Inject constructor(
    val context: Context,
    private val dataManager: DataManager,
    private val api: ApiServices
) : BaseRepository(dataManager, context) {

    suspend fun getOrders(
         page: Int
    ): Response<OrderResponseModel> =
        api.getOrders( 10,"past-orders", page)



}