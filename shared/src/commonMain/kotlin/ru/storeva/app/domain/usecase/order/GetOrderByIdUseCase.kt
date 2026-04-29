package ru.storeva.app.domain.usecase.order

import kotlinx.coroutines.flow.Flow
import ru.storeva.app.domain.models.OrderModel
import ru.storeva.app.domain.models.ResultModel
import ru.storeva.app.domain.repositories.OrderRepository
import ru.storeva.app.domain.usecase.base.IOUseCase

class GetOrderByIdUseCase(
    private val orderRepository: OrderRepository,
) : IOUseCase<Long, ResultModel<OrderModel>>() {
    override fun execute(param: Long): Flow<ResultModel<OrderModel>> {
        return orderRepository.getOrderById(param)
    }
}