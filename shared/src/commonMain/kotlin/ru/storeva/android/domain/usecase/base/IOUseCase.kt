package ru.storeva.android.domain.usecase.base

import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.IO
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flowOn

abstract class IOUseCase<in P, R> {
    operator fun invoke(param: P): Flow<R> =
        execute(param)
            .flowOn(Dispatchers.IO)

    protected abstract fun execute(param: P): Flow<R>
}