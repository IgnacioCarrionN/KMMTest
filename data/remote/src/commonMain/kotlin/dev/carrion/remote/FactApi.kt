package dev.carrion.remote

import dev.carrion.remote.extensions.handleResponse
import dev.carrion.remote.models.FactDto
import dev.carrion.remote.models.RemoteResponse
import io.ktor.client.*
import io.ktor.client.request.*

private const val BASE_URL = "https://cat-fact.herokuapp.com"

class FactApi(private val httpClient: HttpClient) {

    suspend fun fetchFact(): RemoteResponse<FactDto> {
        return handleResponse {
            httpClient.get {
                url("$BASE_URL/facts/random")
            }
        }
    }
}