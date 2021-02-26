package ir.org.tavanesh.data.datasource

import ir.org.tavanesh.core.domain.logy.entity.Logy
import ir.org.tavanesh.core.domain.logy.repository.CreateLogyParams

interface LogyLocalDataSource {
    suspend fun createLogy(params: CreateLogyParams): Boolean

    suspend fun clearLogys(): Boolean

    suspend fun getLogys(): List<Logy>
}