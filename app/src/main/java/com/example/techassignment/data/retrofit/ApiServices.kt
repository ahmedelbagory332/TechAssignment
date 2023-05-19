package com.example.techassignment.data.retrofit

import com.example.techassignment.OrderResponseModel
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Headers
import retrofit2.http.Query


interface ApiServices {

    @Headers("Accept: application/json")
    @GET("baskets/cart/")
    suspend fun getOrders(
        @Query("page_size") page_size: Int,
        @Query("status") status: String,
        @Query("page") page: Int
    ): Response<OrderResponseModel>

}