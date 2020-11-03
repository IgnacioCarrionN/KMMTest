package dev.carrion.repository

import dev.carrion.kmmtest.common.FactBo
import dev.carrion.kmmtest.common.utils.Log
import dev.carrion.local.LocalDataSource
import dev.carrion.remote.FactApi
import dev.carrion.remote.models.FactDto
import kotlinx.coroutines.flow.Flow

private const val LOG_TAG = "FactRepository"

class FactRepositoryImpl(private val remote: FactApi, private val local: LocalDataSource) :
    FactRepository {

    override suspend fun fetchFact() {

        when (val newFact = remote.fetchFact().toRepositoryResponse(FactDto::toBo)) {
            is RepositoryResponse.Success -> local.insertItem(
                newFact.data.text,
                newFact.data.createdAt,
                newFact.data.updatedAt
            )
            is RepositoryResponse.Error -> {
                Log.Debug.log(LOG_TAG, newFact.message)
            }
        }

    }

    override suspend fun getFacts(): Flow<List<FactBo>> {
        return local.getAllFacts()
    }

    override suspend fun deleteAll() {
        local.deleteAll()
    }

    override suspend fun getFactById(id: Long): Flow<FactBo> {
        return local.getFactById(id)
    }
}