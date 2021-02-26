package ir.org.tavanesh.data.platform.datasource

import com.haroldadmin.cnradapter.NetworkResponse
import ir.org.tavanesh.data.models.BaseResponse
import ir.org.tavanesh.data.models.ExamQuestionsResponse
import retrofit2.http.*

interface QuestionApi {
    @GET("getQuestions")
    suspend fun getExamQuestions(
        @Header("Authorization") token: String,
        @Query("examId") examId: String,
    ): NetworkResponse<BaseResponse<ExamQuestionsResponse>, String>


    @FormUrlEncoded
    @POST("setEpisodeAnswer")
    suspend fun answerQuestion(
        @Header("Authorization") token:String,
        @Field("episodeId") episodeId : String,
        @Field("answers") answers : String,
    ): NetworkResponse<BaseResponse<Any>, String>


}