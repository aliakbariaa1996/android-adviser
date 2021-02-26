package ir.org.tavanesh.data.impl.repository

import com.github.kittinunf.result.Result
import ir.org.tavanesh.core.domain.city.entity.City
import ir.org.tavanesh.core.domain.city.repository.CityRepository
import ir.org.tavanesh.core.domain.city.repository.GetAllCityProvincesParams
import ir.org.tavanesh.core.domain.city.repository.GetCitiesOfProvinceParams
import ir.org.tavanesh.core.domain.city.repository.GetProvincesParams
import ir.org.tavanesh.core.utils.Failure
import ir.org.tavanesh.data.datasource.CityLocalDataSource
import ir.org.tavanesh.data.datasource.CityRemoteDataSource
import ir.org.tavanesh.data.mappers.toCityEntity
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import timber.log.Timber
import javax.inject.Inject

class CityRepositoryImpl @Inject constructor(
    private val cityRemoteDataSource: CityRemoteDataSource,
    private val cityLocalDataSource: CityLocalDataSource,
): CityRepository{
    override suspend fun getAllCityProvinces(params: GetAllCityProvincesParams): Result<List<City>, Failure> {
        return withContext(Dispatchers.IO) {
            try {
                val shouldReGet = cityLocalDataSource.shouldReGetCitiesFromServer()
                if(shouldReGet){
                    val result = cityRemoteDataSource.getAllCityProvinces(params)
                    cityLocalDataSource.insertCities(result.toCityEntity())
                    Result.success(result)
                }
                else{
                    Result.success(mutableListOf())
                }
            } catch (e:Failure){
                Result.error(e)
            }
        }
    }

    override suspend fun getCitiesOfProvinces(params: GetCitiesOfProvinceParams): Result<List<City>, Failure> {
        return withContext(Dispatchers.IO) {
            try {
                val result = cityLocalDataSource.getCitiesOfProvinces(params)
                Result.success(result)
            } catch (e:Failure){
                Result.error(e)
            }
        }
    }

    override suspend fun getProvinces(params: GetProvincesParams): Result<List<City>, Failure> {
        return withContext(Dispatchers.IO) {
            try {
                val result = cityLocalDataSource.getProvinces(params)
                Result.success(result)
            } catch (e:Failure){
                Result.error(e)
            }
        }
    }
}