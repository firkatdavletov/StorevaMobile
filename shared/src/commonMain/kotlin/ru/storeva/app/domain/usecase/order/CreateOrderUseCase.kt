package ru.storeva.app.domain.usecase.order

import kotlinx.coroutines.flow.Flow
import ru.storeva.app.domain.models.AddressModel
import ru.storeva.app.domain.models.DeliveryType
import ru.storeva.app.domain.models.OrderItemModel
import ru.storeva.app.domain.models.OrderModel
import ru.storeva.app.domain.models.ResultModel
import ru.storeva.app.domain.repositories.OrderRepository
import ru.storeva.app.domain.usecase.base.IOUseCase

class CreateOrderUseCase(
    private val orderRepository: OrderRepository,
) : IOUseCase<CreateOrderUseCase.Params, ResultModel<OrderModel>>() {
    override fun execute(param: Params): Flow<ResultModel<OrderModel>> {
        return orderRepository.createOrder(
            deliveryType = param.deliveryType,
            deliveryAddress = param.deliveryAddress,
            departmentId = param.departmentId,
            products = param.products,
            amount = param.amount,
            deliveryPrice = param.deliveryPrice,
            comment = param.comment,
        )
    }

    class Params(
        val deliveryType: DeliveryType,
        val deliveryAddress: AddressModel?,
        val comment: String?,
        val departmentId: Long,
        val products: List<OrderItemModel>,
        val amount: Long,
        val deliveryPrice: Long,
    )
}