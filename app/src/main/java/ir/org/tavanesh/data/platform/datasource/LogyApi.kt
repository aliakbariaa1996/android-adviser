package ir.org.tavanesh.data.platform.datasource

import com.haroldadmin.cnradapter.NetworkResponse
import ir.org.tavanesh.data.models.BaseResponse
import retrofit2.http.Body
import retrofit2.http.Header
import retrofit2.http.POST

interface LogyApi {

    @POST("x")
    suspend fun sendLogys(
        @Header("Authorization") token:String,
        @Body body: String,
    ): NetworkResponse<BaseResponse<Any>, String>

}