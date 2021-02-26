package ir.org.tavanesh.data.platform.datasource

import com.haroldadmin.cnradapter.NetworkResponse
import ir.org.tavanesh.data.models.BaseResponse
import ir.org.tavanesh.data.models.CourseModel
import ir.org.tavanesh.data.models.CourseResponse
import ir.org.tavanesh.data.models.ErrorObject
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.Query

interface CourseApi {

    @GET("getCourses")
    suspend fun getCourses(
        @Header("Authorization") token: String,
        @Query("pageId") page: Int,
    ): NetworkResponse<BaseResponse<CourseResponse>, ErrorObject>

    @GET("getCourse")
    suspend fun getCourse(
        @Header("Authorization") token: String,
        @Query("courseId") courseId: String,
    ): NetworkResponse<BaseResponse<CourseModel>, String>


}