package ir.org.tavanesh.core.domain.user.usecase

import com.github.kittinunf.result.Result
import ir.org.tavanesh.core.domain.user.repository.UpdateUserParams
import ir.org.tavanesh.core.domain.user.repository.UserRepository
import ir.org.tavanesh.core.utils.Failure
import ir.org.tavanesh.core.utils.NoParamsFailure
import ir.org.tavanesh.core.utils.Usecase
import javax.inject.Inject

class UpdateUserUseCase @Inject constructor(
    private val userRepository: UserRepository
) : Usecase<Boolean, UpdateUserParams>() {
    override suspend fun call(
        params: UpdateUserParams?
    ): Result<Boolean, Failure> {
        return params?.let {
            userRepository.updateUser(params)
        } ?:run { Result.error(NoParamsFailure()) }
    }
}
