package com.example.techassignment.ui.currentOrders.adapter

import android.annotation.SuppressLint
import android.content.Context
import android.os.Build
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.annotation.RequiresApi
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView
import com.example.techassignment.R
import com.example.techassignment.Result
import com.example.techassignment.databinding.CurrentOrderShipmentItemBinding
import com.example.techassignment.util.loadImage
import java.text.SimpleDateFormat
import java.util.*


class ShipmentAdapter(
    private var myData: Result,
    private val context: Context,
    ) : RecyclerView.Adapter<ShipmentAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {

        return ViewHolder(
            CurrentOrderShipmentItemBinding.inflate(
                LayoutInflater.from(parent.context),
                parent, false
            )
        )
    }


    fun setMyData(myData: Result) {
        this.myData = myData
        notifyDataSetChanged()
    }

    @RequiresApi(Build.VERSION_CODES.N)
    @SuppressLint("SetTextI18n", "ResourceAsColor")
    override fun onBindViewHolder(holder: ViewHolder, position: Int) {

        val model = myData
        holder.onBind(model, position)

    }

    override fun getItemCount(): Int {
        return myData.shipments.size
    }

    inner class ViewHolder(var binding: CurrentOrderShipmentItemBinding) :
        RecyclerView.ViewHolder(binding.root) {

        @SuppressLint("SetTextI18n")
        fun onBind(model: Result, position: Int) {

            binding.shipmentNumberTv.text = "Shipment  ${position + 1}"
            when (model.orderStatusCode) {
                0L -> {
                    binding.statusPaidCard.visibility = View.GONE
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
                    binding.statusPaidCard.visibility = View.GONE
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

            val dateFormat = SimpleDateFormat("yyyy-MM-dd", Locale.getDefault())
            val date = dateFormat.parse(model.shipments[position].deliveryDate)

            val dayOfWeekFormat = SimpleDateFormat("EEEE", Locale.getDefault())
            val dayOfMonthFormat = SimpleDateFormat("d", Locale.getDefault())
            val monthFormat = SimpleDateFormat("MMMM", Locale.getDefault())

            val dayOfWeekString = dayOfWeekFormat.format(date)
            val dayOfMonthString = dayOfMonthFormat.format(date)
            val monthString = monthFormat.format(date)

            val formattedString = "$dayOfWeekString  $dayOfMonthString $monthString"

            binding.shipmentDateTv.text =
                "${formattedString}, ${model.shipments[position].intervalStartTime12} - ${model.shipments[position].intervalEndTime12}"
            binding.productImage.loadImage(model.items[0].image)
            if (model.items.size == 1) {
                binding.itemTotalNumberCard.visibility = View.GONE
            } else {
                binding.total.text = "+${model.items.size - 1}"

            }

        }


    }
}