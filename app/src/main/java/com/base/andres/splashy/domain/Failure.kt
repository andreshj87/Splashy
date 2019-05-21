package com.base.andres.splashy.domain

sealed class Failure {
    object NetworkConnection: Failure()
    object ApiError: Failure()
    object MappingError: Failure()
}