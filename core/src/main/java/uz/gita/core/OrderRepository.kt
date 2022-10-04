package uz.gita.core

import androidx.lifecycle.LiveData

interface OrderRepository {

    fun placingOrder(orderData: OrderData): LiveData<Result<Unit>>
    fun getAllOrder(): LiveData<Result<List<OrderData>>>
    fun getAllOrderTwo(): LiveData<Result<List<OrderData>>>
}