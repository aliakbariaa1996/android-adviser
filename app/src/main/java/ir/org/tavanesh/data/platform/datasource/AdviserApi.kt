package ir.org.tavanesh.data.platform.datasource

import com.haroldadmin.cnradapter.NetworkResponse
import ir.org.tavanesh.data.models.*
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.Query

interface AdviserApi {

    @GET("getCenters")
    suspend fun getAdviceCenters(
        @Header("Authorization") token: String,
        @Query("pageId") page: Int,
        @Query("provinceId") provinceId: Int,
        @Query("cityId") cityId: Int,
    ): NetworkResponse<BaseResponse<AdviceCentersResponse>, String>

    @GET("getCenter")
    suspend fun getAdviceCenter(
        @Header("Authorization") token: String,
        @Query("centerId") centerId: String,
    ): NetworkResponse<BaseResponse<AdviceCenterModel>, String>

    @GET("getAdvisors")
    suspend fun getAdvisers(
        @Header("Authorization") token: String,
        @Query("pageId") page: Int,
        @Query("centerId") centerId: String,
    ): NetworkResponse<BaseResponse<AdvisersResponse>, String>

    @GET("getAdvisor")
    suspend fun getAdviser(
        @Header("Authorization") token: String,
        @Query("advisorId") adviserId: String,
    ): NetworkResponse<BaseResponse<AdviserModel>, String>

    @GET("getTimesForDateByAdvisor")
    suspend fun getAdviserFreeTimes(
        @Header("Authorization") token: String,
        @Query("advisorId") adviserId: String,
        @Query("date") date: String,
    ): NetworkResponse<BaseResponse<List<AdvisersFreeTimeModel>>, String>
}
