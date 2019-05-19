package com.base.andres.splashy.domain

import kotlinx.coroutines.*

abstract class UseCase<out Type, in Params> {
    abstract suspend fun execute(params: Params): Type

    operator fun invoke(params: Params, job: Job, onResult: (Type) -> Unit = {}) {
        val backgroundJob = CoroutineScope(job + Dispatchers.IO).async { execute(params) }
        CoroutineScope(job + Dispatchers.Main).launch { onResult(backgroundJob.await()) }
    }
}