package dev.carrion.remote.models

sealed class RemoteResponse<out T> {

    data class Success<T>(val data: T) : RemoteResponse<T>()

    data class Error(val error: String) : RemoteResponse<Nothing>()

}