package ir.org.tavanesh.data.datasource

import ir.org.tavanesh.core.domain.logy.repository.SendLogysParams

interface LogyRemoteDataSource {
    suspend fun sendLogys(params: SendLogysParams): Boolean
}