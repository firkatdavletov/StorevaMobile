package ru.storeva.app.core.usecase

import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flowOn

abstract class FlowUseCase<in P, R>(
    private val dispatcher: CoroutineDispatcher,
) {
    operator fun invoke(param: P): Flow<R> =
        execute(param)
            .flowOn(dispatcher)

    protected abstract fun execute(param: P): Flow<R>
}