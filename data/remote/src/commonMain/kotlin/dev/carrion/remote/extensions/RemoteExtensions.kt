package dev.carrion.remote.extensions


import dev.carrion.remote.models.RemoteResponse
import io.ktor.client.call.*
import io.ktor.client.statement.*

suspend inline fun <reified T> handleResponse(block: () -> HttpResponse): RemoteResponse<T> {
    return try {
        val response: HttpResponse = block()

        try {
            RemoteResponse.Success(response.receive())
        } catch (e: Exception) {
            RemoteResponse.Error(e.message ?: "Unknown error message")
        }

    } catch (e: Exception) {
        RemoteResponse.Error(e.message ?: "Unknown error message")
    }
}