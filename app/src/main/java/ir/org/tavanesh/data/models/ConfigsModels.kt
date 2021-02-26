package ir.org.tavanesh.data.models

import com.google.gson.annotations.SerializedName


data class ConfigsResponse(
    @field:SerializedName("educations") var educations: List<EnumConfigResponse>,
    @field:SerializedName("genders") var genders: List<EnumConfigResponse>,
)

data class EnumConfigResponse(
    @field:SerializedName("id") var id: Int,
    @field:SerializedName("key") var key: String,
    @field:SerializedName("name") var name: String,
)
