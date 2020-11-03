package dev.carrion.remote.extensions

import dev.carrion.remote.models.FactDto
import dev.carrion.remote.models.RemoteResponse
import dev.carrion.remote.models.StatusDto
import io.ktor.client.*
import io.ktor.client.engine.*
import io.ktor.client.engine.mock.*
import io.ktor.client.features.*
import io.ktor.client.features.json.*
import io.ktor.client.features.json.serializer.*
import io.ktor.client.request.*
import io.ktor.client.request.request
import io.ktor.client.statement.*
import io.ktor.http.*
import io.ktor.util.*
import io.ktor.util.date.*
import io.mockk.called
import io.mockk.internalSubstitute
import io.mockk.verify
import kotlinx.coroutines.runBlocking
import kotlinx.serialization.encodeToString
import kotlinx.serialization.json.Json
import org.json.JSONException
import org.junit.Test
import java.lang.Exception
import kotlin.test.assertEquals


class HandleResponseTestJUnit {

    private val factDto: FactDto =
        FactDto(
            used = false,
            source = "user",
            type = "cat",
            deleted = false,
            id = "5c609ae8e54902001453303b",
            v = 2,
            text = "Approximately 40% of cats have a dominant paw. The rest are ambidextrous.",
            updatedAt = "2020-08-23T20:20:01.611Z",
            createdAt = "2019-02-10T21:43:04.512Z",
            status = StatusDto(
                verified = true,
                sentCount = 1
            ),
            user = "5a9ac18c7478810ea6c06381"
        )

    private val factString: String =
        "{\n" +
                "  \"used\": false,\n" +
                "  \"source\": \"user\",\n" +
                "  \"type\": \"cat\",\n" +
                "  \"deleted\": false,\n" +
                "  \"_id\": \"5c609ae8e54902001453303b\",\n" +
                "  \"updatedAt\": \"2020-08-23T20:20:01.611Z\",\n" +
                "  \"createdAt\": \"2019-02-10T21:43:04.512Z\",\n" +
                "  \"user\": \"5a9ac18c7478810ea6c06381\",\n" +
                "  \"text\": \"Approximately 40% of cats have a dominant paw. The rest are ambidextrous.\",\n" +
                "  \"__v\": 0,\n" +
                "  \"status\": {\n" +
                "    \"verified\": true,\n" +
                "    \"sentCount\": 1\n" +
                "  }\n" +
                "}"

    @Test
    fun shouldReturnSuccessWithString() = runBlocking {

        val mockEngine = MockEngine.config {
            addHandler { respondOk("Hola") }
        }

        val client = HttpClient(mockEngine) { expectSuccess = false }

        val expected = RemoteResponse.Success("Hola")

        val response = handleResponse<String> {
            client.get()
        }
        print(response.toString())
        val actual = (response as RemoteResponse.Success).data

        assertEquals(expected.data, actual)
    }

    @Test
    fun shouldReturnErrorWithString() = runBlocking {
        val mockEngine = MockEngine.config {
            addHandler { respondError(status = HttpStatusCode.ServiceUnavailable, content = "Test Error")}
        }

        val client = HttpClient(mockEngine) { expectSuccess = false }

        val expected = RemoteResponse.Error("Test Error")

        val response = handleResponse<String> {
            client.get()
        }
        print(response.toString())
        val actual = (response as RemoteResponse.Success).data

        assertEquals(expected.error, actual)
    }

    @InternalAPI
    @Test
    fun shouldReturnSuccessWithTestObject() = runBlocking {
        val mockEngine = MockEngine.config {
            addHandler { HttpResponseData(statusCode = HttpStatusCode.OK, requestTime = GMTDate(), Headers.Empty, HttpProtocolVersion.HTTP_2_0, body = factDto, callContext = callContext()) }
        }.create() as MockEngine

        val client = HttpClient(mockEngine) {
            expectSuccess = false
            install(JsonFeature) {
                serializer = KotlinxSerializer()
                accept(ContentType.Application.Json)
            }
        }

        val expected = RemoteResponse.Success(factDto)

        val response = handleResponse<FactDto> {
            client.get()
        }

        print(response.toString())
        val actual = (response as RemoteResponse.Success).data

        assertEquals(expected.data, actual)
    }

    @InternalAPI
    @Test
    fun shouldReturnErrorWithException() = runBlocking {

        val expected = RemoteResponse.Error("JSON")

        val actual = handleResponse<FactDto> { throw Exception("JSON") }

        assertEquals(expected.error, (actual as RemoteResponse.Error).error)
    }


}