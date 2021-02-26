package ir.org.tavanesh.core.domain.advisor.usecase

import com.github.kittinunf.result.Result
import ir.org.tavanesh.core.domain.advisor.entity.Adviser
import ir.org.tavanesh.core.domain.advisor.repository.AdviserRepository
import ir.org.tavanesh.core.domain.advisor.repository.GetAdvisersParams
import ir.org.tavanesh.core.utils.Failure
import ir.org.tavanesh.core.utils.NoParamsFailure
import ir.org.tavanesh.core.utils.Usecase
import javax.inject.Inject

class GetAdvisersUseCase @Inject constructor(
    private val adviserRepository: AdviserRepository
) : Usecase<List<Adviser>, GetAdvisersParams>() {
    override suspend fun call(
        params: GetAdvisersParams?
    ): Result<List<Adviser>, Failure> {
        return params?.let {
            adviserRepository.getAdvisers(params)
        } ?: run { Result.error(NoParamsFailure()) }
    }
}
