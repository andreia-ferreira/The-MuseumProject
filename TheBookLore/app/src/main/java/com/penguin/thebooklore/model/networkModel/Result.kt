package com.penguin.thebooklore.model.networkModel

sealed class Result<out T : Any>
class Success<out T : Any>(val data: T) : Result<T>()
class Error(private val exception: Throwable, val message: String? = exception.localizedMessage) : Result<Nothing>()