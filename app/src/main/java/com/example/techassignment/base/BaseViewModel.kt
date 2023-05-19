package com.example.techassignment.base


import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import com.example.techassignment.R
import retrofit2.Response
import com.example.techassignment.util.SingleLiveEvent
import com.example.techassignment.util.Status

private const val TAG = "BaseViewModel"

abstract class BaseViewModel(private val repository: BaseRepository) : ViewModel() {



    fun <T> performNetworkCall(
        apiCall: suspend () -> Response<T>,
        status: SingleLiveEvent<Status> = SingleLiveEvent<Status>(),
    ) {
        if (repository.isNetworkConnected())
            viewModelScope.launch {
                withContext(Dispatchers.Default) {


                    try {
                        status.postValue(Status.Loading)
                        val response = apiCall.invoke()
                        status.postValue(handleResponse(response))

                    } catch (e: Exception) {
                        Log.d(TAG, "performNetworkCall: ${e.message}")
                        status.postValue(
                            Status.Error(
                                message = repository.getString(R.string.some_thing_went_wrong_error_msg)
                            )
                        )

                    }
                }
            }
        else {
            status.postValue(Status.Error(message = repository.getString(R.string.check_internet_connection)))

        }
    }

    private fun <T> handleResponse(response: Response<T>): Status {

        Log.d(TAG, "handleResponse: ${response.body().toString()}")

        if (response.code() in 200..300) {
            return if (response.isSuccessful) {
                Status.Success(response.body())
            } else {

                if (response.code() == 401) {
                    Status.Unauthorized
                } else {

                    try {
                        Status.Error(response.code(), response.message())
                    } catch (e: Exception) {
                        Status.Error(response.code(), response.message())
                    }
                }
            }
        } else if (response.code() == 401) {

            return Status.Unauthorized
        } else {


            return  try {
                Status.Error(response.code(), response.message())
            } catch (e: Exception) {
                Status.Error(response.code(), response.message())
            }

        }
    }

    fun <T> checkResponse(response: Response<T>): Boolean {
        Log.d(TAG, "handleResponse: ${response.body().toString()}")

        return when (handleResponse(response)) {
            is Status.Success<*> -> {
                true
            }
            else -> {
                false
            }
        }
    }

    val lang: String
        get() = repository.lang


    fun clearData() {
        repository.clearData()
    }


}