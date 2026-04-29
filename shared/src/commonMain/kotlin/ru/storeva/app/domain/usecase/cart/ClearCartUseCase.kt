package ru.storeva.app.domain.usecase.cart

import kotlinx.coroutines.flow.Flow
import ru.storeva.app.domain.models.ResultModel
import ru.storeva.app.domain.repositories.CartRepository
import ru.storeva.app.domain.usecase.base.IOUseCase

class ClearCartUseCase(
    private val cartRepository: CartRepository,
) : IOUseCase<Unit, ResultModel<Boolean>>() {
    override fun execute(param: Unit): Flow<ResultModel<Boolean>> {
        return cartRepository.removeAll()
    }
}