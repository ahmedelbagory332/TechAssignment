package com.example.techassignment.ui.currentOrders

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
class CurrentOrdersViewModel @Inject constructor(
    private val repository: CurrentOrdersRepository
) : BaseViewModel(repository) {


     fun getOrders(): Flow<PagingData<Result>> {
        return Pager(config = PagingConfig(pageSize = 10, enablePlaceholders = false),
            pagingSourceFactory = { CurrentOrdersSource(this, repository) }).flow.cachedIn(
            viewModelScope
        )
    }




}


