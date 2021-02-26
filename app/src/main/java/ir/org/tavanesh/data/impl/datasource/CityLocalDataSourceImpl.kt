package ir.org.tavanesh.data.impl.datasource

import ir.org.tavanesh.core.domain.city.entity.City
import ir.org.tavanesh.core.domain.city.repository.GetCitiesOfProvinceParams
import ir.org.tavanesh.core.domain.city.repository.GetProvincesParams
import ir.org.tavanesh.data.datasource.CityLocalDataSource
import ir.org.tavanesh.data.mappers.toCity
import ir.org.tavanesh.data.models.CityEntity
import ir.org.tavanesh.data.platform.datasource.CityDao
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import javax.inject.Inject

class CityLocalDataSourceImpl @Inject constructor(
    private val cityDao: CityDao
): CityLocalDataSource {
    override suspend fun getCitiesOfProvinces(params: GetCitiesOfProvinceParams): List<City> {
        return withContext(Dispatchers.IO){
            val list = cityDao.getCities(params.provinceId)
            list.toCity()
        }
    }

    override suspend fun getProvinces(params: GetProvincesParams): List<City> {
        return withContext(Dispatchers.IO){
            val list = cityDao.getProvinces()
            list.toCity()
        }
    }

    override suspend fun insertCities(cities: List<CityEntity>):Boolean {
        return withContext(Dispatchers.IO){
            cities.forEach {
                cityDao.insert(it)
            }
            true
        }
    }

    override suspend fun shouldReGetCitiesFromServer():Boolean {
        return withContext(Dispatchers.IO){
            val recordCount = cityDao.countRecords()
            (recordCount==0)
        }
    }
}