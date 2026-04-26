package ru.storeva.android.domain.usecase.user

import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.onEach
import ru.storeva.android.domain.models.ResultModel
import ru.storeva.android.domain.repositories.OrderRepository
import ru.storeva.android.domain.repositories.TokenRepository
import ru.storeva.android.domain.repositories.UserRepository
import ru.storeva.android.domain.usecase.base.IOUseCase

class LogoutUserUseCase(
    private val userRepository: UserRepository,
    private val tokenRepository: TokenRepository,
    private val orderRepository: OrderRepository,
) : IOUseCase<Unit, ResultModel<Boolean>>() {
    override fun execute(param: Unit): Flow<ResultModel<Boolean>> {
        return userRepository
            .logout()
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