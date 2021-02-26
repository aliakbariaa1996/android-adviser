package ir.org.tavanesh.core.domain.city.usecase

import com.github.kittinunf.result.Result
import ir.org.tavanesh.core.domain.city.entity.City
import ir.org.tavanesh.core.domain.city.repository.CityRepository
import ir.org.tavanesh.core.domain.city.repository.GetProvincesParams
import ir.org.tavanesh.core.utils.Failure
import ir.org.tavanesh.core.utils.NoParamsFailure
import ir.org.tavanesh.core.utils.Usecase
import javax.inject.Inject

class GetProvincesUseCase @Inject constructor(
    private val cityRepository: CityRepository
) : Usecase<List<City>, GetProvincesParams>() {
    override suspend fun call(
        params: GetProvincesParams?
    ): Result<List<City>, Failure> {
        return params?.let {
            cityRepository.getProvinces(params)
        } ?: run { Result.error(NoParamsFailure()) }
    }
}
