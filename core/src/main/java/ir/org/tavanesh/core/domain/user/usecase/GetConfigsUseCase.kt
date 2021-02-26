package ir.org.tavanesh.core.domain.user.usecase

import com.github.kittinunf.result.Result
import ir.org.tavanesh.core.domain.user.entity.Configs
import ir.org.tavanesh.core.domain.user.repository.AuthRepository
import ir.org.tavanesh.core.domain.user.repository.GetConfigsParams
import ir.org.tavanesh.core.utils.Failure
import ir.org.tavanesh.core.utils.NoParamsFailure
import ir.org.tavanesh.core.utils.Usecase
import javax.inject.Inject

class GetConfigsUseCase @Inject constructor(
    private val authRepository: AuthRepository
) : Usecase<Configs, GetConfigsParams>() {
    override suspend fun call(
        params: GetConfigsParams?
    ): Result<Configs, Failure> {
        return params?.let {
            authRepository.getConfigs(params)
        } ?:run { Result.error(NoParamsFailure()) }
    }
}
