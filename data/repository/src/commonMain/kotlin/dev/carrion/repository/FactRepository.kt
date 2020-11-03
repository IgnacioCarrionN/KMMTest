package dev.carrion.repository

import dev.carrion.kmmtest.common.FactBo
import kotlinx.coroutines.flow.Flow

interface FactRepository {

    suspend fun fetchFact()

    suspend fun getFacts(): Flow<List<FactBo>>

    suspend fun deleteAll()

    suspend fun getFactById(id: Long): Flow<FactBo>
}