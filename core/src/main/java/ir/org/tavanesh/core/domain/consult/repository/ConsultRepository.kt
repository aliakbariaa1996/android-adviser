package ir.org.tavanesh.core.domain.consult.repository

import com.github.kittinunf.result.Result
import ir.org.tavanesh.core.domain.consult.entity.Consult
import ir.org.tavanesh.core.utils.Failure

interface ConsultRepository {
    suspend fun getConsultingInfo(params: GetConsultingInfoParams): Result<Consult, Failure>

    suspend fun getConsultingHistory(params: GetConsultingHistoryParams): Result<List<Consult>, Failure>

    suspend fun submitConsult(params: SubmitConsultingParams): Result<Consult, Failure>
}