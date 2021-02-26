package ir.org.tavanesh.data.impl.datasource

import ir.org.tavanesh.core.domain.city.entity.City
import ir.org.tavanesh.core.domain.city.repository.GetAllCityProvincesParams
import ir.org.tavanesh.core.utils.Failure
import ir.org.tavanesh.core.utils.ProviderFailure
import ir.org.tavanesh.core.utils.UnKnownFailure
import ir.org.tavanesh.core.utils.provider_internet
import ir.org.tavanesh.data.datasource.CityRemoteDataSource
import ir.org.tavanesh.data.mappers.toCities
import ir.org.tavanesh.data.mappers.waitResponse
import ir.org.tavanesh.data.platform.datasource.CityApi
import ir.org.tavanesh.data.platform.datasource.ResourcesRepository
import javax.inject.Inject

class CityRemoteDataSourceImpl @Inject constructor(
    private val cityApi: CityApi,
    private val resourcesRepository: ResourcesRepository,
) : CityRemoteDataSource {
    override suspend fun getAllCityProvinces(params: GetAllCityProvincesParams): List<City> {
        return if (resourcesRepository.isInternetConnected()) {
            try {
                cityApi.getAllCityProvinces()
                    .waitResponse { response ->
                        val all = mutableListOf<City>()
                        all.addAll(response.items.toCities())
                        all
                    }
            } catch (e: Exception) {
                if (e is Failure) throw e
                else throw UnKnownFailure()
            }
        } else {
            throw ProviderFailure(provider_internet)
        }
    }
}