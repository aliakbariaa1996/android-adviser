package ir.org.tavanesh.core.domain.advisor.usecase

import com.github.kittinunf.result.Result
import ir.org.tavanesh.core.domain.advisor.entity.AdviceCenter
import ir.org.tavanesh.core.domain.advisor.repository.AdviserRepository
import ir.org.tavanesh.core.domain.advisor.repository.GetAdviceCentersParams
import ir.org.tavanesh.core.utils.Failure
import ir.org.tavanesh.core.utils.NoParamsFailure
import ir.org.tavanesh.core.utils.Usecase
import javax.inject.Inject

class GetAdviceCentersUseCase @Inject constructor(
    private val adviserRepository: AdviserRepository
) : Usecase<List<AdviceCenter>, GetAdviceCentersParams>() {
    override suspend fun call(
        params: GetAdviceCentersParams?
    ): Result<List<AdviceCenter>, Failure> {
        return params?.let {
            adviserRepository.getAdviceCenters(params)
        } ?: run { Result.error(NoParamsFailure()) }
    }
}
