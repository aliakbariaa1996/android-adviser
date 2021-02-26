package ir.org.tavanesh.data.models

import com.google.gson.annotations.SerializedName

data class BaseResponse<T>(
    @field:SerializedName("result") val data: T,
    @field:SerializedName("message") val message: String,
)

data class ErrorObject(
    @field:SerializedName("message") val message: String,
)