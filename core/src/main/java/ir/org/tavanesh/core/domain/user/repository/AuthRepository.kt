package ir.org.tavanesh.core.domain.user.repository

import com.github.kittinunf.result.Result
import ir.org.tavanesh.core.domain.user.entity.Configs
import ir.org.tavanesh.core.domain.user.entity.User
import ir.org.tavanesh.core.utils.Failure

interface AuthRepository {
    suspend fun forgetPassword(params: ForgetPasswordParams): Result<String, Failure>

    suspend fun loginUser(params: LoginUserParams): Result<User, Failure>

    suspend fun registerUser(params: RegisterUserParams): Result<User, Failure>

    suspend fun logoutUser(params: LogoutUserParams): Result<Boolean, Failure>

    suspend fun setPassword(params: SetPasswordParams): Result<Boolean, Failure>

    suspend fun checkMobile(params: CheckMobileParams): Result<String, Failure>

    suspend fun checkVerificationCode(params: CheckVerificationCodeParams): Result<String, Failure>

    suspend fun getConfigs(params: GetConfigsParams): Result<Configs, Failure>
}