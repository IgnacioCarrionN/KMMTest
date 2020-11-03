package dev.carrion.repository

import dev.carrion.remote.models.RemoteResponse

sealed class RepositoryResponse<out T> {

    data class Success<T>(val data: T) : RepositoryResponse<T>()

    data class Error(val message: String) : RepositoryResponse<Nothing>()

}

fun <T, S> RemoteResponse<T>.toRepositoryResponse(boMapper: T.() -> S) : RepositoryResponse<S> {
    return when(this) {
        is RemoteResponse.Success<T> -> RepositoryResponse.Success(data = this.data.boMapper())
        is RemoteResponse.Error -> RepositoryResponse.Error(message = this.error)
    }
}