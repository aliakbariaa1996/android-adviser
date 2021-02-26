package ir.org.tavanesh.core.domain.user.usecase

import com.github.kittinunf.result.Result
import ir.org.tavanesh.core.domain.user.repository.UpdateUserAppUsageParams
import ir.org.tavanesh.core.domain.user.repository.UserRepository
import ir.org.tavanesh.core.utils.Failure
import ir.org.tavanesh.core.utils.NoParamsFailure
import ir.org.tavanesh.core.utils.Usecase
import javax.inject.Inject

class UpdateUserAppUsageUseCase @Inject constructor(
    private val userRepository: UserRepository
) : Usecase<Boolean, UpdateUserAppUsageParams>() {
    override suspend fun call(
        params: UpdateUserAppUsageParams?
    ): Result<Boolean, Failure> {
        return params?.let {
            userRepository.updateUserAppUsage(params)
        } ?:run { Result.error(NoParamsFailure()) }
    }
}
