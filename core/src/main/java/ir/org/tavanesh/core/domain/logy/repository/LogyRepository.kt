package ir.org.tavanesh.core.domain.logy.repository

import com.github.kittinunf.result.Result
import ir.org.tavanesh.core.utils.Failure

interface LogyRepository {
    suspend fun createLogy(params: CreateLogyParams): Result<Boolean, Failure>

    suspend fun sendLogys(params: SendLogysParams): Result<Boolean, Failure>
}