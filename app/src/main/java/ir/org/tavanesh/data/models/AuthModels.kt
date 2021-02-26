package ir.org.tavanesh.data.models

import com.google.gson.annotations.SerializedName


data class ForgetPasswordResponse(
    @field:SerializedName("secretKey") var secretKey: String,
)

data class CheckMobileNumberResponse(
    @field:SerializedName("secretKey") var secretKey: String,
)

data class AuthResponse(
    @field:SerializedName("accessToken") var accessToken: String,
)
