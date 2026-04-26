package ru.storeva.android.domain.usecase.cart

import kotlinx.coroutines.flow.Flow
import ru.storeva.android.domain.models.ProductModel
import ru.storeva.android.domain.models.ResultModel
import ru.storeva.android.domain.repositories.CartRepository
import ru.storeva.android.domain.usecase.base.IOUseCase

class RemoveFromCartUseCase(
    private val cartRepository: CartRepository,
) : IOUseCase<RemoveFromCartUseCase.Params, ResultModel<Boolean>>() {
    override fun execute(param: Params): Flow<ResultModel<Boolean>> {
        return cartRepository.updateQuantity(param.product)
    }

    data class Params(val product: ProductModel)
}