package ir.org.tavanesh.core.domain.user.usecase

import com.github.kittinunf.result.Result
import ir.org.tavanesh.core.domain.user.entity.UserAppUsage
import ir.org.tavanesh.core.domain.user.repository.GetUserAppUsageParams
import ir.org.tavanesh.core.domain.user.repository.UserRepository
import ir.org.tavanesh.core.utils.Failure
import ir.org.tavanesh.core.utils.NoParamsFailure
import ir.org.tavanesh.core.utils.Usecase
import javax.inject.Inject

class GetUserAppUsageUseCase @Inject constructor(
    private val userRepository: UserRepository
) : Usecase<UserAppUsage, GetUserAppUsageParams>() {
    override suspend fun call(
        params: GetUserAppUsageParams?
    ): Result<UserAppUsage, Failure> {
        return params?.let {
            userRepository.getUserAppUsage(params)
        } ?:run { Result.error(NoParamsFailure()) }
    }
}
