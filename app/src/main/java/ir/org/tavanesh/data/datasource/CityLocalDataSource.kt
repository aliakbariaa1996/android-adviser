package ir.org.tavanesh.data.datasource

import ir.org.tavanesh.core.domain.city.entity.City
import ir.org.tavanesh.core.domain.city.repository.GetCitiesOfProvinceParams
import ir.org.tavanesh.core.domain.city.repository.GetProvincesParams
import ir.org.tavanesh.data.models.CityEntity

interface CityLocalDataSource {
    suspend fun getCitiesOfProvinces(params: GetCitiesOfProvinceParams): List<City>

    suspend fun getProvinces(params: GetProvincesParams): List<City>

    suspend fun insertCities(cities:List<CityEntity>):Boolean

    suspend fun shouldReGetCitiesFromServer():Boolean
}