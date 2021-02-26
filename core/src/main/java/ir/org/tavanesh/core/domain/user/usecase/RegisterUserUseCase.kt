package ir.org.tavanesh.core.domain.user.usecase

import com.github.kittinunf.result.Result
import ir.org.tavanesh.core.domain.user.entity.User
import ir.org.tavanesh.core.domain.user.repository.AuthRepository
import ir.org.tavanesh.core.domain.user.repository.RegisterUserParams
import ir.org.tavanesh.core.utils.Failure
import ir.org.tavanesh.core.utils.NoParamsFailure
import ir.org.tavanesh.core.utils.Usecase
import javax.inject.Inject

class RegisterUserUseCase @Inject constructor(
    private val authRepository: AuthRepository
) : Usecase<User, RegisterUserParams>() {
    override suspend fun call(
        params: RegisterUserParams?
    ): Result<User, Failure> {
        return params?.let {
            authRepository.registerUser(params)
        } ?:run { Result.error(NoParamsFailure()) }
    }
}
