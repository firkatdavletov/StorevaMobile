package ru.storeva.android.domain.usecase.auth

import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import ru.storeva.android.domain.repositories.TokenRepository
import ru.storeva.android.domain.usecase.base.IOUseCase

class GetAccessTokenUseCase(
    private val tokenRepository: TokenRepository,
) : IOUseCase<Unit, String?>() {
    override fun execute(param: Unit): Flow<String?> {
        return flow {
            val token = tokenRepository.getAccessToken()
            emit(token)
        }
    }
}