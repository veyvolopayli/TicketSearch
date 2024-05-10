package com.veyvolopayli.common

sealed class RequestResult<T> {
    class Success<T>(val data: T) : RequestResult<T>()
    class Loading<T>(val data: T? = null) : RequestResult<T>()
    class Failure<T>(val data: T? = null, val error: Throwable) : RequestResult<T>()
}