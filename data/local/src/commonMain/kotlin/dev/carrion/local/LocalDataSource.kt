package dev.carrion.local

import com.squareup.sqldelight.runtime.coroutines.asFlow
import com.squareup.sqldelight.runtime.coroutines.mapToList
import com.squareup.sqldelight.runtime.coroutines.mapToOne
import dev.carrion.kmmtest.common.FactBo
import kotlinx.coroutines.flow.Flow

interface LocalDataSource {

    fun getAllFacts(): Flow<List<FactBo>>

    fun insertItem(text: String, createdAt: String, updatedAt: String)

    fun deleteAll()

    fun getFactById(id: Long): Flow<FactBo>

}

class LocalDataSourceImpl(private val local: KMMTestQueries): LocalDataSource {

    override fun getAllFacts(): Flow<List<FactBo>> {
        return local.selectAll(
            mapper = { id, text, createdAt, updatedAt ->
                FactBo(id = id.toString(), text = text, createdAt = createdAt, updatedAt = updatedAt)
            }
        ).asFlow().mapToList()
    }

    override fun insertItem(text: String, createdAt: String, updatedAt: String) {
        local.insertItem(text, createdAt, updatedAt)
    }

    override fun deleteAll() {
        local.deleteAll()
    }

    override fun getFactById(id: Long): Flow<FactBo> {
        return local.selectItem(id = id, mapper =  { _id, text, createdAt, updatedAt ->
            FactBo(id = _id.toString(), text = text, createdAt = createdAt, updatedAt = updatedAt)
        }).asFlow().mapToOne()
    }
}