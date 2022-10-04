package uz.gita.core

object Repository {

    val orderRepository: OrderRepository by lazy { OrderRepositoryImpl() }
}