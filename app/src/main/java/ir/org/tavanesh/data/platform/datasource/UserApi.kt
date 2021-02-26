package ir.org.tavanesh.data.platform.datasource

import com.haroldadmin.cnradapter.NetworkResponse
import ir.org.tavanesh.data.models.BaseResponse
import ir.org.tavanesh.data.models.UserResponse
import okhttp3.MultipartBody
import okhttp3.RequestBody
import retrofit2.http.*

interface UserApi {

    @GET("userInfo")
    suspend fun getUser(
        @Header("Authorization") token:String,
    ): NetworkResponse<BaseResponse<UserResponse>, String>

    @Multipart
    @PUT("userEdit")
    suspend fun updateUser(
        @Header("Authorization") token:String,
        @Part("username") username: RequestBody,
        @Part("email") email : RequestBody,
        @Part("age") age : RequestBody,
        @Part avatar: MultipartBody.Part? = null
    ): NetworkResponse<BaseResponse<Any>, String>
}