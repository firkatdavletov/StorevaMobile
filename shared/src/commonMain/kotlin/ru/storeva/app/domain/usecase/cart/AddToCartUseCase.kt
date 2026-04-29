package ru.storeva.app.domain.usecase.cart

import kotlinx.coroutines.flow.Flow
import ru.storeva.app.domain.models.ProductModel
import ru.storeva.app.domain.models.ResultModel
import ru.storeva.app.domain.repositories.CartRepository
import ru.storeva.app.domain.usecase.base.IOUseCase

class AddToCartUseCase(
    private val cartRepository: CartRepository,
) : IOUseCase<AddToCartUseCase.Params, ResultModel<Boolean>>() {
    override fun execute(param: Params): Flow<ResultModel<Boolean>> {
        return cartRepository.updateQuantity(param.product)
    }

    data class Params(val product: ProductModel)
}