package com.example.techassignment.ui.previousOrders

import android.annotation.SuppressLint
import android.content.Context
import android.os.Build
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.annotation.RequiresApi
import androidx.core.content.ContextCompat
import androidx.paging.PagingDataAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.techassignment.R
import com.example.techassignment.Result
import com.example.techassignment.databinding.PreviousOrderShipmentItemBinding
import com.example.techassignment.util.loadImage
import java.text.SimpleDateFormat
import java.util.*

class PreviousOrdersAdapter(private val context: Context) : PagingDataAdapter<Result, PreviousOrdersAdapter.ViewHolder>(Result.CALLBACK) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {

        return  ViewHolder(
            PreviousOrderShipmentItemBinding.inflate(
                LayoutInflater.from(parent.context),
                parent, false
            )
        )


    }

    @RequiresApi(Build.VERSION_CODES.N)
    @SuppressLint("SetTextI18n", "ResourceAsColor")
    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val model = getItem(position)!!
        holder.bind(model,position)
    }



    inner class ViewHolder(var binding: PreviousOrderShipmentItemBinding) :
        RecyclerView.ViewHolder(binding.root) {
        @RequiresApi(Build.VERSION_CODES.M)
        @SuppressLint("SetTextI18n")
        fun bind(model: Result, position: Int) {

            binding.shipmentNumberTv.text = "Order Number #${model.orderId}"

            val dateFormat = SimpleDateFormat("yyyy-MM-dd", Locale.getDefault())
            val date = dateFormat.parse(model.orderDate)

            val dayOfWeekFormat = SimpleDateFormat("EEEE", Locale.getDefault())
            val dayOfMonthFormat = SimpleDateFormat("d", Locale.getDefault())
            val monthFormat = SimpleDateFormat("MMMM", Locale.getDefault())

            val dayOfWeekString = dayOfWeekFormat.format(date)
            val dayOfMonthString = dayOfMonthFormat.format(date)
            val monthString = monthFormat.format(date)

            val formattedString = "$dayOfWeekString  $dayOfMonthString $monthString"
            binding.shipmentDateTv.text = "Request at  $formattedString , ${model.orderTime}"
            binding.totalTV.text = "${model.cartTotal} SAR"

            when (model.orderStatusCode) {
                0L -> {
                    binding.statusOrder.text = "Canceled"
                    binding.statusOrderCard.setCardBackgroundColor(
                        ContextCompat.getColor(
                            context,
                            R.color.cancel
                        )
                    )
                    binding.imageView.setImageResource(R.drawable.cancel_icon)
                }
                5L -> {

                }
                else -> {
                    binding.statusOrder.text = "Preparing"
                    binding.statusOrderCard.setCardBackgroundColor(
                        ContextCompat.getColor(
                            context,
                            R.color.prepare
                        )
                    )
                    binding.imageView.setImageResource(R.drawable.prepare)
                }
            }

            binding.productImage.loadImage(model.items[0].image)
            if (model.items.size == 1) {
                binding.itemTotalNumberCard.visibility = View.GONE
            } else {
                binding.total.text = "+${model.items.size - 1}"

            }




        }
    }



}