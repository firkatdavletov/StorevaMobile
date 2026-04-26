package ru.storeva.android.domain.usecase.order

import kotlinx.coroutines.flow.Flow
import ru.storeva.android.domain.models.OrderModel
import ru.storeva.android.domain.repositories.OrderRepository
import ru.storeva.android.domain.usecase.base.IOUseCase

class GetOrdersUseCase(
    private val orderRepository: OrderRepository,
) : IOUseCase<Unit, List<OrderModel>>() {
    override fun execute(param: Unit): Flow<List<OrderModel>> {
        return orderRepository.getOrders()
    }
}