package ir.org.tavanesh.core.domain.advisor.usecase

import com.github.kittinunf.result.Result
import ir.org.tavanesh.core.domain.advisor.entity.AdviceCenter
import ir.org.tavanesh.core.domain.advisor.repository.AdviserRepository
import ir.org.tavanesh.core.domain.advisor.repository.GetAdviceCenterParams
import ir.org.tavanesh.core.utils.Failure
import ir.org.tavanesh.core.utils.NoParamsFailure
import ir.org.tavanesh.core.utils.Usecase
import javax.inject.Inject

class GetAdviceCenterUseCase @Inject constructor(
    private val adviserRepository: AdviserRepository
) : Usecase<AdviceCenter, GetAdviceCenterParams>() {
    override suspend fun call(
        params: GetAdviceCenterParams?
    ): Result<AdviceCenter, Failure> {
        return params?.let {
            adviserRepository.getAdviceCenter(params)
        } ?: run { Result.error(NoParamsFailure()) }
    }
}
