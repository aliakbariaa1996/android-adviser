package ir.org.tavanesh.core.domain.city.usecase

import com.github.kittinunf.result.Result
import ir.org.tavanesh.core.domain.city.entity.City
import ir.org.tavanesh.core.domain.city.repository.CityRepository
import ir.org.tavanesh.core.domain.city.repository.GetCitiesOfProvinceParams
import ir.org.tavanesh.core.utils.Failure
import ir.org.tavanesh.core.utils.NoParamsFailure
import ir.org.tavanesh.core.utils.Usecase
import javax.inject.Inject

class GetCitiesOfProvinceUseCase @Inject constructor(
    private val cityRepository: CityRepository
) : Usecase<List<City>, GetCitiesOfProvinceParams>() {
    override suspend fun call(
        params: GetCitiesOfProvinceParams?
    ): Result<List<City>, Failure> {
        return params?.let {
            cityRepository.getCitiesOfProvinces(params)
        } ?: run { Result.error(NoParamsFailure()) }
    }
}
