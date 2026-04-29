package ru.storeva.app.core.usecase

import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.withContext

abstract class SuspendUseCase<in P, R>(
    private val dispatcher: CoroutineDispatcher,
) {
    suspend operator fun invoke(param: P): R =
        withContext(dispatcher) {
            execute(param)
        }

    protected abstract suspend fun execute(param: P): R
}