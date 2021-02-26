package ir.org.tavanesh.data.datasource

import ir.org.tavanesh.core.domain.user.entity.Configs
import ir.org.tavanesh.core.domain.user.entity.User
import ir.org.tavanesh.core.domain.user.repository.*

interface AuthRemoteDataSource {

    suspend fun forgetPassword(params: ForgetPasswordParams): String

    suspend fun loginUser(params: LoginUserParams): String

    suspend fun registerUser(params: RegisterUserParams): User

    suspend fun logoutUser(params: LogoutUserParams): Boolean

    suspend fun setPassword(params: SetPasswordParams): Boolean

    suspend fun checkMobile(params: CheckMobileParams): String

    suspend fun checkVerificationCode(params: CheckVerificationCodeParams): String

    suspend fun getConfigs(params: GetConfigsParams): Configs
}