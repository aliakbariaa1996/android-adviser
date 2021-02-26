package ir.org.tavanesh.data.platform.datasource

import com.haroldadmin.cnradapter.NetworkResponse
import ir.org.tavanesh.data.models.*
import retrofit2.http.*

interface AuthApi {

    @GET("forgotPassword")
    suspend fun forgetPassword(
        @Query("mobileNumber") mobileNumber:String,
    ): NetworkResponse<BaseResponse<ForgetPasswordResponse>, String>

    @GET("login")
    suspend fun login(
        @Query("username") username:String,
        @Query("password") password:String,
    ): NetworkResponse<BaseResponse<AuthResponse>, String>

    @DELETE("logout")
    suspend fun logout(
        @Header("Authorization") token:String,
    ): NetworkResponse<BaseResponse<Any>, String>

    @GET("checkMobileNumber")
    suspend fun checkMobileNumber(
        @Query("mobileNumber") mobileNumber:String,
    ): NetworkResponse<BaseResponse<CheckMobileNumberResponse>, String>

    @GET("checkVerificationCode")
    suspend fun checkVerificationCode(
        @Query("code") code:String,
        @Query("secretKey") secretKey:String,
    ): NetworkResponse<BaseResponse<AuthResponse>, String>

    @FormUrlEncoded
    @PUT("register")
    suspend fun register(
        @Header("Authorization") token:String,
        @Field("username") username: String,
        @Field("nationalCode") nationalCode: String,
        @Field("firstname") firstname: String,
        @Field("lastname") lastname: String,
        @Field("password") password: String,
        @Field("age") age: Int?,
        @Field("gender") gender: String,
        @Field("email") email: String?,
        @Field("education") education: String,
    ): NetworkResponse<BaseResponse<UserResponse>, String>

    @FormUrlEncoded
    @POST("changePassword")
    suspend fun changePassword(
        @Header("Authorization") token:String,
        @Field("password") password: String,
    ): NetworkResponse<BaseResponse<Any>, String>

    @GET("getConfig")
    suspend fun getConfigs(
    ): NetworkResponse<BaseResponse<ConfigsResponse>, String>
}