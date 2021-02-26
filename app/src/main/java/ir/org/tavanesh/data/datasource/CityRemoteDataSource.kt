package ir.org.tavanesh.data.datasource

import ir.org.tavanesh.core.domain.city.entity.City
import ir.org.tavanesh.core.domain.city.repository.GetAllCityProvincesParams

interface CityRemoteDataSource {

    suspend fun getAllCityProvinces(params: GetAllCityProvincesParams): List<City>

}