package ir.org.tavanesh.core.domain.user.usecase

import com.github.kittinunf.result.Result
import ir.org.tavanesh.core.domain.user.repository.AuthRepository
import ir.org.tavanesh.core.domain.user.repository.CheckVerificationCodeParams
import ir.org.tavanesh.core.utils.Failure
import ir.org.tavanesh.core.utils.NoParamsFailure
import ir.org.tavanesh.core.utils.Usecase
import javax.inject.Inject

class CheckVerificationCodeUseCase @Inject constructor(
    private val authRepository: AuthRepository
) : Usecase<String, CheckVerificationCodeParams>() {
    override suspend fun call(
        params: CheckVerificationCodeParams?
    ): Result<String, Failure> {
        return params?.let {
            authRepository.checkVerificationCode(params)
        } ?: run { Result.error(NoParamsFailure()) }
    }
}
