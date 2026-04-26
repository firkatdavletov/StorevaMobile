package ru.storeva.android.domain.usecase.cart

import kotlinx.coroutines.flow.Flow
import ru.storeva.android.domain.models.AddressModel
import ru.storeva.android.domain.models.DeliveryInfoModel
import ru.storeva.android.domain.models.DeliveryType
import ru.storeva.android.domain.models.ResultModel
import ru.storeva.android.domain.repositories.CartRepository
import ru.storeva.android.domain.usecase.base.IOUseCase

class CreateCartUseCase(
    private val cartRepository: CartRepository,
) : IOUseCase<CreateCartUseCase.Params, ResultModel<Boolean>>() {
    override fun execute(param: Params): Flow<ResultModel<Boolean>> {
        return cartRepository.createCart(
            deliveryType = param.deliveryType,
            deliveryAddress = param.deliveryAddress,
            departmentId = param.departmentId,
            deliveryInfo = param.deliveryInfoModel,
        )
    }

    class Params(
        val deliveryType: DeliveryType,
        val deliveryAddress: AddressModel?,
        val departmentId: Long,
        val deliveryInfoModel: DeliveryInfoModel?,
    ) {
        constructor(deliveryAddress: AddressModel, deliveryInfo: DeliveryInfoModel, departmentId: Long) : this(
            deliveryType = DeliveryType.DELIVERY,
            deliveryAddress = deliveryAddress,
            departmentId = departmentId,
            deliveryInfoModel = deliveryInfo,
        )
        constructor(departmentId: Long) : this(
            deliveryType = DeliveryType.PICKUP,
            deliveryAddress = null,
            departmentId = departmentId,
            deliveryInfoModel = null,
        )
    }
}