package ir.org.tavanesh.core.domain.user.usecase

import com.github.kittinunf.result.Result
import ir.org.tavanesh.core.domain.user.entity.User
import ir.org.tavanesh.core.domain.user.repository.GetUserParams
import ir.org.tavanesh.core.domain.user.repository.UserRepository
import ir.org.tavanesh.core.utils.Failure
import ir.org.tavanesh.core.utils.NoParamsFailure
import ir.org.tavanesh.core.utils.Usecase
import javax.inject.Inject

class GetUserUseCase @Inject constructor(
    private val userRepository: UserRepository
) : Usecase<User, GetUserParams>() {
    override suspend fun call(
        params: GetUserParams?
    ): Result<User, Failure> {
        return params?.let {
            userRepository.getUser(params)
        } ?:run { Result.error(NoParamsFailure()) }
    }
}
