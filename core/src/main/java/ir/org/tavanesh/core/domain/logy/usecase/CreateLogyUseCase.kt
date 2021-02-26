package ir.org.tavanesh.core.domain.logy.usecase

import com.github.kittinunf.result.Result
import ir.org.tavanesh.core.domain.logy.repository.CreateLogyParams
import ir.org.tavanesh.core.domain.logy.repository.LogyRepository
import ir.org.tavanesh.core.utils.Failure
import ir.org.tavanesh.core.utils.NoParamsFailure
import ir.org.tavanesh.core.utils.Usecase
import javax.inject.Inject

class CreateLogyUseCase @Inject constructor(
    private val logyRepository: LogyRepository
) : Usecase<Boolean, CreateLogyParams>() {
    override suspend fun call(
        params: CreateLogyParams?
    ): Result<Boolean, Failure> {
        return params?.let {
            logyRepository.createLogy(params)
        } ?: run { Result.error(NoParamsFailure()) }
    }
}