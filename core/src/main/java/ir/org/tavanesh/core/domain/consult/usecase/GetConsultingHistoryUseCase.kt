package ir.org.tavanesh.core.domain.consult.usecase

import com.github.kittinunf.result.Result
import ir.org.tavanesh.core.domain.consult.entity.Consult
import ir.org.tavanesh.core.domain.consult.repository.ConsultRepository
import ir.org.tavanesh.core.domain.consult.repository.GetConsultingHistoryParams
import ir.org.tavanesh.core.utils.Failure
import ir.org.tavanesh.core.utils.NoParamsFailure
import ir.org.tavanesh.core.utils.Usecase
import javax.inject.Inject

class GetConsultingHistoryUseCase @Inject constructor(
    private val consultRepository: ConsultRepository
) : Usecase<List<Consult>, GetConsultingHistoryParams>() {
    override suspend fun call(
        params: GetConsultingHistoryParams?
    ): Result<List<Consult>, Failure> {
        return params?.let {
            consultRepository.getConsultingHistory(params)
        } ?: run { Result.error(NoParamsFailure()) }
    }
}
