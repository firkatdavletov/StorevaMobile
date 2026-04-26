package ru.storeva.android.domain.usecase.cart

import kotlinx.coroutines.flow.Flow
import ru.storeva.android.domain.models.AddressModel
import ru.storeva.android.domain.models.DeliveryInfoModel
import ru.storeva.android.domain.models.DeliveryType
import ru.storeva.android.domain.models.ResultModel
import ru.storeva.android.domain.repositories.CartRepository
import ru.storeva.android.domain.usecase.base.IOUseCase

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