package ru.storeva.app.domain.usecase.order

import kotlinx.coroutines.flow.Flow
import ru.storeva.app.domain.models.OrderModel
import ru.storeva.app.domain.repositories.OrderRepository
import ru.storeva.app.domain.usecase.base.IOUseCase

class GetOrdersUseCase(
    private val orderRepository: OrderRepository,
) : IOUseCase<Unit, List<OrderModel>>() {
    override fun execute(param: Unit): Flow<List<OrderModel>> {
        return orderRepository.getOrders()
    }
}