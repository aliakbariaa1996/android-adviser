package ir.org.tavanesh.core.domain.user.usecase

import com.github.kittinunf.result.Result
import ir.org.tavanesh.core.domain.user.entity.User
import ir.org.tavanesh.core.domain.user.repository.AuthRepository
import ir.org.tavanesh.core.domain.user.repository.LoginUserParams
import ir.org.tavanesh.core.utils.Failure
import ir.org.tavanesh.core.utils.NoParamsFailure
import ir.org.tavanesh.core.utils.Usecase
import javax.inject.Inject

class LoginUserUseCase @Inject constructor(
    private val authRepository: AuthRepository
) : Usecase<User, LoginUserParams>() {
    override suspend fun call(
        params: LoginUserParams?
    ): Result<User, Failure> {
        return params?.let {
            authRepository.loginUser(params)
        } ?:run { Result.error(NoParamsFailure()) }
    }

}