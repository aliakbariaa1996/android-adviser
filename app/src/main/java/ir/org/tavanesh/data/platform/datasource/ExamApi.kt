package ir.org.tavanesh.data.platform.datasource

import com.haroldadmin.cnradapter.NetworkResponse
import ir.org.tavanesh.data.models.*
import retrofit2.http.*

interface ExamApi {

    @GET("getExams")
    suspend fun getExams(
        @Header("Authorization") token: String,
        @Query("pageId") page: Int,
    ): NetworkResponse<BaseResponse<ExamsResponse>, String>

    @GET("getExam")
    suspend fun getExam(
        @Header("Authorization") token: String,
        @Query("examId") examId: String,
    ): NetworkResponse<BaseResponse<ExamModel>, String>


    @FormUrlEncoded
    @POST("setExamAnswer")
    suspend fun setExamAnswer(
        @Header("Authorization") token:String,
        @Field("examId") examId : String,
        @Field("answers") answers : String,
    ): NetworkResponse<BaseResponse<FeedbackResponse>, String>
}