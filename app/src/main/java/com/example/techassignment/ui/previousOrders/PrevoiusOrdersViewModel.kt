package com.example.techassignment.ui.previousOrders

import androidx.lifecycle.viewModelScope
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import androidx.paging.cachedIn
import com.example.techassignment.Result
import com.example.techassignment.base.BaseViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

@HiltViewModel
class PrevoiusOrdersViewModel @Inject constructor(
    private val repository: PreviousOrdersRepository
) : BaseViewModel(repository) {


     fun getOrders(): Flow<PagingData<Result>> {
        return Pager(config = PagingConfig(pageSize = 10, enablePlaceholders = false),
            pagingSourceFactory = { PreviousOrdersSource(this, repository) }).flow.cachedIn(
            viewModelScope
        )
    }




}


