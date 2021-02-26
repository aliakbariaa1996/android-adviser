package ir.org.tavanesh.data.models

import com.google.gson.annotations.SerializedName

data class UserResponse(
    @field:SerializedName("age") var age: String,
    @field:SerializedName("education") var education: String,
    @field:SerializedName("email") var email: String,
    @field:SerializedName("firstname") var firstName: String,
    @field:SerializedName("gender") var gender: String,
    @field:SerializedName("id") var id: String,
    @field:SerializedName("lastname") var lastName: String,
    @field:SerializedName("mobileNumber") var mobileNumber: String,
    @field:SerializedName("nationalCode") var nationalCode: String,
    @field:SerializedName("status") var status: String,
    @field:SerializedName("username") var username: String,
    @field:SerializedName("avatar") var avatar: String,
)