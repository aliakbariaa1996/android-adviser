package ir.org.tavanesh.core.domain.user.usecase

import com.github.kittinunf.result.Result
import ir.org.tavanesh.core.domain.user.repository.AuthRepository
import ir.org.tavanesh.core.domain.user.repository.LogoutUserParams
import ir.org.tavanesh.core.utils.Failure
import ir.org.tavanesh.core.utils.NoParamsFailure
import ir.org.tavanesh.core.utils.Usecase
import javax.inject.Inject

class LogoutUserUseCase @Inject constructor(
    private val authRepository: AuthRepository
) : Usecase<Boolean, LogoutUserParams>() {
    override suspend fun call(
        params: LogoutUserParams?
    ): Result<Boolean, Failure> {
        return params?.let {
            authRepository.logoutUser(params)
        } ?:run { Result.error(NoParamsFailure()) }
    }

}