package ir.org.tavanesh.core.domain.logy.usecase

import com.github.kittinunf.result.Result
import ir.org.tavanesh.core.domain.logy.repository.LogyRepository
import ir.org.tavanesh.core.domain.logy.repository.SendLogysParams
import ir.org.tavanesh.core.utils.Failure
import ir.org.tavanesh.core.utils.NoParamsFailure
import ir.org.tavanesh.core.utils.Usecase
import javax.inject.Inject

class SendLogysUseCase @Inject constructor(
    private val logyRepository: LogyRepository
) : Usecase<Boolean, SendLogysParams>() {
    override suspend fun call(
        params: SendLogysParams?
    ): Result<Boolean, Failure> {
        return params?.let {
            logyRepository.sendLogys(params)
        } ?: run { Result.error(NoParamsFailure()) }
    }
}