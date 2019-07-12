package com.base.andres.splashy.domain.usecase

import com.base.andres.splashy.domain.Either
import com.base.andres.splashy.domain.Failure
import kotlinx.coroutines.*

abstract class UseCase<out Type, in Params> {
    abstract suspend fun execute(params: Params): Either<Failure, Type>

    operator fun invoke(scope: CoroutineScope, params: Params, onResult: (Either<Failure, Type>) -> Unit = {}) {
        val backgroundJob = scope.async { execute(params) }
        scope.launch { onResult(backgroundJob.await()) }
    }

    class None
}