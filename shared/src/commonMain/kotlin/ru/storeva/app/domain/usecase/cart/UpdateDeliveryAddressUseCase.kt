package ru.storeva.app.domain.usecase.cart

import kotlinx.coroutines.flow.Flow
import ru.storeva.app.domain.models.AddressModel
import ru.storeva.app.domain.models.DeliveryInfoModel
import ru.storeva.app.domain.models.DeliveryType
import ru.storeva.app.domain.models.ResultModel
import ru.storeva.app.domain.repositories.CartRepository
import ru.storeva.app.domain.usecase.base.IOUseCase

class UpdateDeliveryAddressUseCase(
    private val cartRepository: CartRepository,
) : IOUseCase<UpdateDeliveryAddressUseCase.Params, ResultModel<Boolean>>() {
    override fun execute(param: Params): Flow<ResultModel<Boolean>> {
        return cartRepository.updateDeliveryAddress(
            deliveryType = param.deliveryType,
            deliveryAddress = param.deliveryAddress,
            departmentId = param.departmentId,
            deliveryInfo = param.deliveryInfo,
            comment = param.comment,
        )
    }

    data class Params(
        val deliveryAddress: AddressModel? = null,
        val deliveryType: DeliveryType,
        val departmentId: Long,
        val deliveryInfo: DeliveryInfoModel,
        val comment: String? = null,
    )
}