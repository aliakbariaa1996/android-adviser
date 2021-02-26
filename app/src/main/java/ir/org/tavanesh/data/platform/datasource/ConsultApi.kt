package ir.org.tavanesh.data.platform.datasource

import com.haroldadmin.cnradapter.NetworkResponse
import ir.org.tavanesh.data.models.BaseResponse
import ir.org.tavanesh.data.models.ConsultModel
import ir.org.tavanesh.data.models.ConsultsResponse
import ir.org.tavanesh.data.models.SubmitConsultModel
import retrofit2.http.*

interface ConsultApi {

    @GET("getConsultations")
    suspend fun getConsultations(
        @Header("Authorization") token:String,
        @Query("pageId") pageId:Int,
    ): NetworkResponse<BaseResponse<ConsultsResponse>, String>

    @GET("getConsultation")
    suspend fun getConsultation(
        @Header("Authorization") token:String,
        @Query("consultationId") consultationId:String,
    ): NetworkResponse<BaseResponse<ConsultModel>, String>

    @FormUrlEncoded
    @POST("createConsultation")
    suspend fun createConsultation(
        @Header("Authorization") token:String,
        @Field("advisorId") advisorId : String,
        @Field("date") date : String,
        @Field("timeId") timeId : String,
        @Field("centerId") centerId : String,
    ): NetworkResponse<BaseResponse<SubmitConsultModel>, String>

}