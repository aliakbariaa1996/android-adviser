package ir.org.tavanesh.data.platform.datasource

import com.haroldadmin.cnradapter.NetworkResponse
import ir.org.tavanesh.data.models.BaseResponse
import ir.org.tavanesh.data.models.CityProvinceResponse
import retrofit2.http.GET

interface CityApi {

    @GET("getProvinceAndCites")
    suspend fun getAllCityProvinces(
    ): NetworkResponse<BaseResponse<CityProvinceResponse>, String>
}