package ir.org.tavanesh.core.domain.advisor.usecase

import com.github.kittinunf.result.Result
import ir.org.tavanesh.core.domain.advisor.entity.Adviser
import ir.org.tavanesh.core.domain.advisor.repository.AdviserRepository
import ir.org.tavanesh.core.domain.advisor.repository.GetAdviserParams
import ir.org.tavanesh.core.utils.Failure
import ir.org.tavanesh.core.utils.NoParamsFailure
import ir.org.tavanesh.core.utils.Usecase
import javax.inject.Inject

class GetAdviserUseCase @Inject constructor(
    private val adviserRepository: AdviserRepository
) : Usecase<Adviser, GetAdviserParams>() {
    override suspend fun call(
        params: GetAdviserParams?
    ): Result<Adviser, Failure> {
        return params?.let {
            adviserRepository.getAdviser(params)
        } ?: run { Result.error(NoParamsFailure()) }
    }
}
