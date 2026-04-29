package ru.storeva.app.domain.usecase.user

import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.onEach
import ru.storeva.app.domain.models.ResultModel
import ru.storeva.app.domain.repositories.OrderRepository
import ru.storeva.app.domain.repositories.TokenRepository
import ru.storeva.app.domain.repositories.UserRepository
import ru.storeva.app.domain.usecase.base.IOUseCase

class DeleteUserUseCase(
    private val userRepository: UserRepository,
    private val tokenRepository: TokenRepository,
    private val orderRepository: OrderRepository,
) : IOUseCase<Unit, ResultModel<Boolean>>() {
    override fun execute(param: Unit): Flow<ResultModel<Boolean>> {
        return userRepository
            .deleteUser()
            .onEach { resultModel ->
                if (resultModel is ResultModel.Success<Boolean>) {
                    if (resultModel.data) {
                        tokenRepository.saveAccessToken("")
                        tokenRepository.saveRefreshToken("")
                        orderRepository.clearOrders()
                        orderRepository.disconnect()
                    }
                }
            }
    }
}