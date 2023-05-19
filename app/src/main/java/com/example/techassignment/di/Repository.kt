package com.example.techassignment.di



import android.content.Context
import com.example.techassignment.data.shared.DataManager
import com.example.techassignment.ui.currentOrders.CurrentOrdersRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton
import com.example.techassignment.data.retrofit.ApiServices
import com.example.techassignment.ui.previousOrders.PreviousOrdersRepository


@Module
@InstallIn(SingletonComponent::class)
object Repository {
    @Singleton
    @Provides
    fun currentOrdersRepository(
        @ApplicationContext appContext: Context,
        dataManager: DataManager,
        api: ApiServices,
    ): CurrentOrdersRepository =
        CurrentOrdersRepository(appContext, dataManager, api)

    @Singleton
    @Provides
    fun previousOrdersRepository(
        @ApplicationContext appContext: Context,
        dataManager: DataManager,
        api: ApiServices,
    ): PreviousOrdersRepository =
        PreviousOrdersRepository(appContext, dataManager, api)

}