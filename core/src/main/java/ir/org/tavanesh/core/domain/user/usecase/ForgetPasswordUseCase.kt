package ir.org.tavanesh.core.domain.user.usecase

import com.github.kittinunf.result.Result
import ir.org.tavanesh.core.domain.user.repository.AuthRepository
import ir.org.tavanesh.core.domain.user.repository.ForgetPasswordParams
import ir.org.tavanesh.core.utils.Failure
import ir.org.tavanesh.core.utils.NoParamsFailure
import ir.org.tavanesh.core.utils.Usecase
import javax.inject.Inject

class ForgetPasswordUseCase @Inject constructor(
    private val authRepository: AuthRepository
) : Usecase<String, ForgetPasswordParams>() {
    override suspend fun call(
        params: ForgetPasswordParams?
    ): Result<String, Failure> {
        return params?.let {
            authRepository.forgetPassword(params)
        } ?:run { Result.error(NoParamsFailure()) }
    }
}
