package com.example.techassignment

import androidx.recyclerview.widget.DiffUtil
import com.google.gson.annotations.SerializedName


data class OrderResponseModel(
    val next: String,
    val previous: Any?,
    val count: Long,
    @field:SerializedName("num_pages")
    val numPages: Long,
    @field:SerializedName("current_page")
    val currentPage: Int,
    val result: ArrayList<Result>,
    val status: Boolean,
    val other: Any?,
    val message: String,
)

data class Result(
    @field:SerializedName("cart_id")
    val cartId: Long,
    @field:SerializedName("cart_total")
    val cartTotal: Double,
    @field:SerializedName("order_status")
    val orderStatus: String,
    @field:SerializedName("order_status_code")
    val orderStatusCode: Long,
    @field:SerializedName("order_date")
    val orderDate: String,
    @field:SerializedName("order_time")
    val orderTime: String,
    @field:SerializedName("delivered_at_date")
    val deliveredAtDate: String?,
    @field:SerializedName("order_id")
    val orderId: Long,
    @field:SerializedName("order_delivery_rating")
    val orderDeliveryRating: ArrayList<OrderDeliveryRating>,
    val items: ArrayList<Item>,
    @field:SerializedName("items_count")
    val itemsCount: Long,
    val shipments: ArrayList<Shipment>,
    @field:SerializedName("order_rating_status")
    val orderRatingStatus: Long,
){
    companion object {


        val CALLBACK = object : DiffUtil.ItemCallback<Result>() {
            override fun areItemsTheSame(
                oldItem: Result,
                newItem: Result
            ): Boolean {
                return oldItem.cartId === newItem.cartId
            }

            override fun areContentsTheSame(
                oldItem: Result,
                newItem: Result
            ): Boolean =
                oldItem == newItem
        }
    }
}

data class OrderDeliveryRating(
    @field:SerializedName("delivery_date")
    val deliveryDate: String,
    @field:SerializedName("delivery_time")
    val deliveryTime: String,
    val driver: Long,
    @field:SerializedName("driver_name")
    val driverName: String,
    @field:SerializedName("delivery_total")
    val deliveryTotal: Double,
    @field:SerializedName("delivered_today")
    val deliveredToday: Boolean,
    @field:SerializedName("delivery_items")
    val deliveryItems: DeliveryItems,
    @field:SerializedName("delivery_shipments_ids")
    val deliveryShipmentsIds: ArrayList<Long>,
    @field:SerializedName("delivery_rating_status")
    val deliveryRatingStatus: Long,
    @field:SerializedName("order_id")
    val orderId: Long,
)

data class DeliveryItems(
    @field:SerializedName("items_ArrayList")
    val itemsArrayList: ArrayList<String>,
    @field:SerializedName("remaining_items")
    val remainingItems: Long,
)

data class Item(
    val title: String,
    val subtitle: String,
    val image: String,
)

data class Shipment(
    val id: Long,
    @field:SerializedName("shipment_code")
    val shipmentCode: String,
    @field:SerializedName("branch_id")
    val branchId: Long,
    @field:SerializedName("delivery_system")
    val deliverySystem: Long,
    @field:SerializedName("delivery_date")
    val deliveryDate: String,
    @field:SerializedName("interval_start_time")
    val intervalStartTime: String,
    @field:SerializedName("interval_end_time")
    val intervalEndTime: String,
    @field:SerializedName("interval_start_time_12")
    val intervalStartTime12: String,
    @field:SerializedName("interval_end_time_12")
    val intervalEndTime12: String,
    @field:SerializedName("delivery_day")
    val deliveryDay: String,
    @field:SerializedName("delivery_current_status")
    val deliveryCurrentStatus: String,
    @field:SerializedName("delivery_status_code")
    val deliveryStatusCode: Long,
    val items: ArrayList<Item2>,
    val code: String,
    @field:SerializedName("status_header_message")
    val statusHeaderMessage: String,
    @field:SerializedName("status_detailed_message")
    val statusDetailedMessage: String,
    @field:SerializedName("grand_total")
    val grandTotal: String,
    @field:SerializedName("shipment_total")
    val shipmentTotal: String,
    val delivery: Delivery,
    val promocode: Promocode?,
    @field:SerializedName("is_rescheduled")
    val isRescheduled: Boolean,
    @field:SerializedName("delivered_at")
    val deliveredAt: String?,
    @field:SerializedName("driver_id")
    val driverId: Long?,
    @field:SerializedName("driver_name")
    val driverName: String?,
)

data class Item2(
    val id: Long,
    @field:SerializedName("package_id")
    val packageId: Long,
    val sku: String,
    @field:SerializedName("ordered_quantity")
    val orderedQuantity: Long,
    val price: Double,
    @field:SerializedName("discounted_quantity")
    val discountedQuantity: Long,
    val discount: Double,
    @field:SerializedName("unit_price")
    val unitPrice: Double,
    @field:SerializedName("original_quantity")
    val originalQuantity: Long,
    val status: String,
    @field:SerializedName("status_code")
    val statusCode: Long,
    val image: String,
    val title: String,
    @field:SerializedName("consumable_display")
    val consumableDisplay: String,
    val quantity: String,
    @field:SerializedName("sub_title")
    val subTitle: String,
)

data class Delivery(
    @field:SerializedName("delivery_fee")
    val deliveryFee: Double,
)

data class Promocode(
    val code: String,
    @field:SerializedName("cart_discount")
    val cartDiscount: Double,
    @field:SerializedName("delivery_discount")
    val deliveryDiscount: Double,
    @field:SerializedName("total_discount")
    val totalDiscount: Double,
)
