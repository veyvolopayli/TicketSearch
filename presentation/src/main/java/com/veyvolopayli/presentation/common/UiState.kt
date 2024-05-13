package com.veyvolopayli.presentation.common

sealed class UiState<T> {
    class Loading<T> : UiState<T>()
    class Success<T>(val data: T) : UiState<T>()
    class Error<T>(val data: T? = null, val errorMessage: String) : UiState<T>()
}