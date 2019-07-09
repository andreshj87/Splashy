package com.base.andres.splashy.domain.usecase

import com.base.andres.splashy.domain.Either
import com.base.andres.splashy.domain.Failure
import kotlinx.coroutines.*

abstract class UseCase<out Type, in Params> {
    abstract suspend fun execute(params: Params): Either<Failure, Type>

    operator fun invoke(params: Params, job: Job, onResult: (Either<Failure, Type>) -> Unit = {}) {
        val backgroundJob = CoroutineScope(job + Dispatchers.IO).async { execute(params) }
        CoroutineScope(job + Dispatchers.Main).launch { onResult(backgroundJob.await()) }
    }

    class None
}