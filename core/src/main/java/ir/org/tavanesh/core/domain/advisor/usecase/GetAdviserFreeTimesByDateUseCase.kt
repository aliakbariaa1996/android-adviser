package ir.org.tavanesh.core.domain.advisor.usecase

import com.github.kittinunf.result.Result
import ir.org.tavanesh.core.domain.advisor.entity.AdviserTime
import ir.org.tavanesh.core.domain.advisor.repository.AdviserRepository
import ir.org.tavanesh.core.domain.advisor.repository.GetAdviserFreeTimesByDataParams
import ir.org.tavanesh.core.utils.Failure
import ir.org.tavanesh.core.utils.NoParamsFailure
import ir.org.tavanesh.core.utils.Usecase
import javax.inject.Inject


class GetAdviserFreeTimesByDateUseCase @Inject constructor(
    private val adviserRepository: AdviserRepository
) : Usecase<List<AdviserTime>, GetAdviserFreeTimesByDataParams>() {
    override suspend fun call(
        params: GetAdviserFreeTimesByDataParams?
    ): Result<List<AdviserTime>, Failure> {
        return params?.let {
            adviserRepository.getAdviserFreeTimesByDate(params)
        } ?: run { Result.error(NoParamsFailure()) }
    }
}
