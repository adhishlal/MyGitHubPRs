package com.adhishlal.mygithubprs.domain.usecases.base

interface CoroutineUseCaseWithParams<P, R> {
    suspend fun execute(params: P): R
}