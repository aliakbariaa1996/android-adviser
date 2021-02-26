package ir.org.tavanesh.data.impl.repository

import com.github.kittinunf.result.Result
import ir.org.tavanesh.core.domain.consult.entity.Consult
import ir.org.tavanesh.core.domain.consult.repository.ConsultRepository
import ir.org.tavanesh.core.domain.consult.repository.GetConsultingInfoParams
import ir.org.tavanesh.core.domain.consult.repository.GetConsultingHistoryParams
import ir.org.tavanesh.core.domain.consult.repository.SubmitConsultingParams
import ir.org.tavanesh.core.utils.Failure
import ir.org.tavanesh.data.datasource.ConsultRemoteDataSource
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import javax.inject.Inject

class ConsultRepositoryImpl @Inject constructor(
    private val consultRemoteDataSource: ConsultRemoteDataSource,
): ConsultRepository {
    override suspend fun getConsultingInfo(params: GetConsultingInfoParams): Result<Consult, Failure> {
        return withContext(Dispatchers.IO) {
            try {
               val result = consultRemoteDataSource.getConsultingInfo(params)
                Result.success(result)
            } catch (e:Failure){
                Result.error(e)
            }
        }
    }

    override suspend fun getConsultingHistory(params: GetConsultingHistoryParams): Result<List<Consult>, Failure> {
        return withContext(Dispatchers.IO) {
            try {
                val result = consultRemoteDataSource.getConsultingHistory(params)
                Result.success(result)
            } catch (e:Failure){
                Result.error(e)
            }
        }
    }

    override suspend fun submitConsult(params: SubmitConsultingParams): Result<Consult, Failure> {
        return withContext(Dispatchers.IO) {
            try {
                val result = consultRemoteDataSource.submitConsult(params)
                Result.success(result)
            } catch (e:Failure){
                Result.error(e)
            }
        }
    }

}