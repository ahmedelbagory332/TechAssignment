package com.example.techassignment.ui.currentOrders.adapter

import android.annotation.SuppressLint
import android.content.Context
import android.os.Build
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.annotation.RequiresApi
import androidx.paging.PagingDataAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.techassignment.Result
import com.example.techassignment.databinding.CurrentOrderItemBinding
import java.text.SimpleDateFormat
import java.util.*

class CurrentOrdersAdapter(private val context: Context) : PagingDataAdapter<Result, CurrentOrdersAdapter.ViewHolder>(Result.CALLBACK) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {

        return  ViewHolder(
            CurrentOrderItemBinding.inflate(
                LayoutInflater.from(parent.context),
                parent, false
            )
        )


    }

    @RequiresApi(Build.VERSION_CODES.N)
    @SuppressLint("SetTextI18n", "ResourceAsColor")
    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val model = getItem(position)!!
        holder.bind(model)
    }



    inner class ViewHolder(var binding: CurrentOrderItemBinding) :
        RecyclerView.ViewHolder(binding.root) {
        @RequiresApi(Build.VERSION_CODES.M)
        @SuppressLint("SetTextI18n")
        fun bind(model: Result) {

            binding.orderNumberTv.text = "Order Number #${model.orderId}"
            val dateFormat = SimpleDateFormat("yyyy-MM-dd", Locale.getDefault())
            val date = dateFormat.parse(model.orderDate)

            val dayOfWeekFormat = SimpleDateFormat("EEEE", Locale.getDefault())
            val dayOfMonthFormat = SimpleDateFormat("d", Locale.getDefault())
            val monthFormat = SimpleDateFormat("MMMM", Locale.getDefault())

            val dayOfWeekString = dayOfWeekFormat.format(date)
            val dayOfMonthString = dayOfMonthFormat.format(date)
            val monthString = monthFormat.format(date)

            val formattedString = "$dayOfWeekString  $dayOfMonthString $monthString"

            binding.orderTimeRequestTv.text = "Request at  ${formattedString}, ${model.orderTime}"

            val adapter = ShipmentAdapter(model,context)
            binding.shipmentList.adapter = adapter
            adapter.setMyData(model)





        }
    }



}