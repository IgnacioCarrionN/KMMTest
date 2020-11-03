package dev.carrion.local

import com.squareup.sqldelight.runtime.coroutines.asFlow
import com.squareup.sqldelight.runtime.coroutines.mapToList
import dev.carrion.kmmtest.common.utils.runBlockingTest
import io.mockk.confirmVerified
import io.mockk.mockk
import io.mockk.verify
import kotlin.test.Test

class LocalDataSourceImplTest {

    private val kmmTestQueries: KMMTestQueries = mockk(relaxed = true)

    private val dataSource: LocalDataSourceImpl = LocalDataSourceImpl(kmmTestQueries)

    @Test
    fun selectAllItemsCallsKmmTestQueries() = runBlockingTest {
        dataSource.getAllFacts()
        verify {
            kmmTestQueries.selectAll(any()).asFlow().mapToList()
        }
        confirmVerified(kmmTestQueries)
    }

    @Test
    fun insertItemsCallKmmTestQueriesWithCorrectData() {
        val text = "hola"
        val createdAt = "1"
        val updatedAt = "1"

        dataSource.insertItem(text, createdAt, updatedAt)

        verify { kmmTestQueries.insertItem(text, createdAt, updatedAt) }

        confirmVerified(kmmTestQueries)
    }
}