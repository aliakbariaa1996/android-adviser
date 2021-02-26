package ir.org.tavanesh.core.domain.consult.usecase

import com.github.kittinunf.result.Result
import ir.org.tavanesh.core.domain.consult.entity.Consult
import ir.org.tavanesh.core.domain.consult.repository.ConsultRepository
import ir.org.tavanesh.core.domain.consult.repository.GetConsultingInfoParams
import ir.org.tavanesh.core.utils.Failure
import ir.org.tavanesh.core.utils.NoParamsFailure
import ir.org.tavanesh.core.utils.Usecase
import javax.inject.Inject

class GetConsultingInfoUseCase @Inject constructor(
    private val consultRepository: ConsultRepository
) : Usecase<Consult, GetConsultingInfoParams>() {
    override suspend fun call(
        params: GetConsultingInfoParams?
    ): Result<Consult, Failure> {
        return params?.let {
            consultRepository.getConsultingInfo(params)
        } ?: run { Result.error(NoParamsFailure()) }
    }
}
