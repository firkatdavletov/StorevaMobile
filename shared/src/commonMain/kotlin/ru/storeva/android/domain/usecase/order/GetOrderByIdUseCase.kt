package ru.storeva.android.domain.usecase.order

import kotlinx.coroutines.flow.Flow
import ru.storeva.android.domain.models.OrderModel
import ru.storeva.android.domain.models.ResultModel
import ru.storeva.android.domain.repositories.OrderRepository
import ru.storeva.android.domain.usecase.base.IOUseCase

class GetOrderByIdUseCase(
    private val orderRepository: OrderRepository,
) : IOUseCase<Long, ResultModel<OrderModel>>() {
    override fun execute(param: Long): Flow<ResultModel<OrderModel>> {
        return orderRepository.getOrderById(param)
    }
}