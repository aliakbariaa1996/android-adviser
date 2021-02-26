package ir.org.tavanesh.data.impl.repository

import com.github.kittinunf.result.Result
import ir.org.tavanesh.core.domain.logy.repository.CreateLogyParams
import ir.org.tavanesh.core.domain.logy.repository.LogyRepository
import ir.org.tavanesh.core.domain.logy.repository.SendLogysParams
import ir.org.tavanesh.core.utils.Failure
import ir.org.tavanesh.data.datasource.LogyLocalDataSource
import ir.org.tavanesh.data.datasource.LogyRemoteDataSource
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import javax.inject.Inject

class LogyRepositoryImpl @Inject constructor(
    private val logyRemoteDataSource: LogyRemoteDataSource,
    private val logyLocalDataSource: LogyLocalDataSource,
): LogyRepository {
    override suspend fun createLogy(params: CreateLogyParams): Result<Boolean, Failure> {
        return withContext(Dispatchers.IO) {
            try {
                val result = logyLocalDataSource.createLogy(params)
                Result.success(result)
            } catch (e:Failure){
                Result.error(e)
            }
        }
    }

    override suspend fun sendLogys(params: SendLogysParams): Result<Boolean, Failure> {
        return withContext(Dispatchers.IO) {
            try {
                val logys = logyLocalDataSource.getLogys()
                val result = logyRemoteDataSource.sendLogys(params)
                logyLocalDataSource.clearLogys()
                Result.success(result)
            } catch (e:Failure){
                Result.error(e)
            }
        }
    }

}