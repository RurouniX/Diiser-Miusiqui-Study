package com.diiser.network.utils

import java.io.IOException

suspend fun <T : Any> safeApiCall(call: suspend () -> ResultType<T>, errorMessage: String = ""): ResultType<T> =
    try {
        call.invoke()
    } catch (e: Exception) {
        ResultType.Fail(IOException(errorMessage, e))
    }

sealed class ResultType<out T : Any> {
    data class Success<out T : Any>(val data: T) : ResultType<T>()
    data class Error<out T : Any>(val data: T) : ResultType<T>()
    data class Fail(val exception: Exception) : ResultType<Nothing>()
}