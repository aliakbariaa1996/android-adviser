package ir.org.tavanesh.data.datasource

import ir.org.tavanesh.core.domain.consult.entity.Consult
import ir.org.tavanesh.core.domain.consult.repository.GetConsultingInfoParams
import ir.org.tavanesh.core.domain.consult.repository.GetConsultingHistoryParams
import ir.org.tavanesh.core.domain.consult.repository.SubmitConsultingParams

interface ConsultRemoteDataSource {
    suspend fun getConsultingInfo(params: GetConsultingInfoParams): Consult

    suspend fun getConsultingHistory(params: GetConsultingHistoryParams): List<Consult>

    suspend fun submitConsult(params: SubmitConsultingParams): Consult
}
