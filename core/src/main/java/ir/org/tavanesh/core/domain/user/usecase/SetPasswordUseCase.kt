package ir.org.tavanesh.core.domain.user.usecase

import com.github.kittinunf.result.Result
import ir.org.tavanesh.core.domain.user.repository.AuthRepository
import ir.org.tavanesh.core.domain.user.repository.SetPasswordParams
import ir.org.tavanesh.core.utils.Failure
import ir.org.tavanesh.core.utils.NoParamsFailure
import ir.org.tavanesh.core.utils.Usecase
import javax.inject.Inject

class SetPasswordUseCase @Inject constructor(
    private val authRepository: AuthRepository
) : Usecase<Boolean, SetPasswordParams>() {
    override suspend fun call(
        params: SetPasswordParams?
    ): Result<Boolean, Failure> {
        return params?.let {
            authRepository.setPassword(params)
        } ?:run { Result.error(NoParamsFailure()) }
    }

}