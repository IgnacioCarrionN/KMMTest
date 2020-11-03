package dev.carrion.repository

import dev.carrion.remote.models.FactDto
import dev.carrion.remote.models.RemoteResponse
import dev.carrion.remote.models.StatusDto
import org.junit.Test
import kotlin.test.assertEquals

class RepositoryResponseTest {

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

    @Test
    fun shouldReturnSuccess() {
        val expected = RepositoryResponse.Success(factDto.toBo())

        val actual = RemoteResponse.Success(factDto).toRepositoryResponse { toBo() }

        assertEquals(expected, actual)
    }

    @Test
    fun shouldReturnError() {
        val expected = RepositoryResponse.Error("TEST")

        val actual = RemoteResponse.Error("TEST").toRepositoryResponse { Unit }

        assertEquals(expected, actual)
    }
}