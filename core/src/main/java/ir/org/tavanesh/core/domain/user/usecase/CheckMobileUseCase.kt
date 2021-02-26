package ir.org.tavanesh.core.domain.user.usecase

import com.github.kittinunf.result.Result
import ir.org.tavanesh.core.domain.user.repository.AuthRepository
import ir.org.tavanesh.core.domain.user.repository.CheckMobileParams
import ir.org.tavanesh.core.utils.Failure
import ir.org.tavanesh.core.utils.NoParamsFailure
import ir.org.tavanesh.core.utils.Usecase
import javax.inject.Inject

class CheckMobileUseCase @Inject constructor(
    private val authRepository: AuthRepository
) : Usecase<String, CheckMobileParams>() {
    override suspend fun call(
        params: CheckMobileParams?
    ): Result<String, Failure> {
        return params?.let {
            authRepository.checkMobile(params)
        } ?: run { Result.error(NoParamsFailure()) }
    }
}
