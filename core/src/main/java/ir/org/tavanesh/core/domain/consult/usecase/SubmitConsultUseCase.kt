package ir.org.tavanesh.core.domain.consult.usecase

import com.github.kittinunf.result.Result
import ir.org.tavanesh.core.domain.consult.entity.Consult
import ir.org.tavanesh.core.domain.consult.repository.ConsultRepository
import ir.org.tavanesh.core.domain.consult.repository.SubmitConsultingParams
import ir.org.tavanesh.core.utils.Failure
import ir.org.tavanesh.core.utils.NoParamsFailure
import ir.org.tavanesh.core.utils.Usecase
import javax.inject.Inject

class SubmitConsultUseCase @Inject constructor(
    private val consultRepository: ConsultRepository
) : Usecase<Consult, SubmitConsultingParams>() {
    override suspend fun call(
        params: SubmitConsultingParams?
    ): Result<Consult, Failure> {
        return params?.let {
            consultRepository.submitConsult(params)
        } ?: run { Result.error(NoParamsFailure()) }
    }
}
