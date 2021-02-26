package ir.org.tavanesh.core.domain.user.repository

import okhttp3.MultipartBody
import okhttp3.RequestBody

data class ForgetPasswordParams(
    var mobile: String,
)

data class LoginUserParams(
    var username: String,
    var password: String,
)

data class RegisterUserParams(
    var firstName: String?=null,
    var gender: String?=null,
    var age: Int?=null,
    var education: String?=null,
    var lastName: String?=null,
    var username: String?=null,// 7-20
    var nationalCode: String?=null,
    var password: String?=null,
    var email: String?=null,
)

data class GetUserParams(
    var isFromLocal: Boolean?=null,
)

data class GetUserAppUsageParams(
    var x: String? = null,
)

data class UpdateUserAppUsageParams(
    var isIntroWatched:Boolean? = null,
    var isFirstExamTake:Boolean? = null,
    var token:String? = null,
    var firebaseToken:String? = null,
)

data class UpdateUserParams(
    var username: RequestBody,
    var email: RequestBody,
    var age: RequestBody,
    var avatar: MultipartBody.Part?,
)

data class LogoutUserParams(
    var sure: Boolean? = null,
)

data class SetPasswordParams(
    var password: String,
)

data class CheckVerificationCodeParams(
    var secretKey: String,
    var verifyCode: String,
)

data class CheckMobileParams(
    var mobile: String,
)

data class GetConfigsParams(
    var x: String?=null,
)