package ir.org.tavanesh.core.domain.city.repository

import com.github.kittinunf.result.Result
import ir.org.tavanesh.core.domain.city.entity.City
import ir.org.tavanesh.core.utils.Failure

interface CityRepository {
    suspend fun getAllCityProvinces(params: GetAllCityProvincesParams): Result<List<City>, Failure>

    suspend fun getCitiesOfProvinces(params: GetCitiesOfProvinceParams): Result<List<City>, Failure>

    suspend fun getProvinces(params: GetProvincesParams): Result<List<City>, Failure>
}
