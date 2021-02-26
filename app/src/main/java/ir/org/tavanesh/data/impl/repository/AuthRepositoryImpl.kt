package ir.org.tavanesh.data.impl.repository

import com.github.kittinunf.result.Result
import ir.org.tavanesh.core.domain.user.entity.Configs
import ir.org.tavanesh.core.domain.user.entity.User
import ir.org.tavanesh.core.domain.user.repository.*
import ir.org.tavanesh.core.utils.Failure
import ir.org.tavanesh.data.datasource.AuthLocalDataSource
import ir.org.tavanesh.data.datasource.AuthRemoteDataSource
import ir.org.tavanesh.data.datasource.UserRemoteDataSource
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import javax.inject.Inject

class AuthRepositoryImpl @Inject constructor(
    private val authRemoteDataSource: AuthRemoteDataSource,
    private val authLocalDataSource: AuthLocalDataSource,
    private val userRemoteDataSource: UserRemoteDataSource,
) : AuthRepository {

    override suspend fun forgetPassword(params: ForgetPasswordParams): Result<String, Failure> {
        return withContext(Dispatchers.IO) {
            try {
                val result = authRemoteDataSource.forgetPassword(params)
                Result.success(result)
            } catch (e:Failure){
                Result.error(e)
            }
        }
    }

    override suspend fun loginUser(params: LoginUserParams): Result<User, Failure> {
        return withContext(Dispatchers.IO) {
            try {
                val token = authRemoteDataSource.loginUser(params)
                authLocalDataSource.setToken(token)
                val user = userRemoteDataSource.getUser(
                    GetUserParams()
                )
                Result.success(user)
            }catch (e:Failure){
                Result.error(e)
            }
        }
    }

    override suspend fun registerUser(params: RegisterUserParams): Result<User, Failure> {
        return withContext(Dispatchers.IO) {
            try {
                val user = authRemoteDataSource.registerUser(params)
                Result.success(user)
            }catch (e:Failure){
                Result.error(e)
            }
        }
    }

    override suspend fun logoutUser(params: LogoutUserParams): Result<Boolean, Failure> {
        return withContext(Dispatchers.IO) {
            try {
                val result = authLocalDataSource.logout()
                authLocalDataSource.logout()
                Result.success(result)
            }catch (e:Failure){
                Result.error(e)
            }
        }
    }

    override suspend fun setPassword(params: SetPasswordParams): Result<Boolean, Failure> {
        return withContext(Dispatchers.IO) {
            try {
                val result = authRemoteDataSource.setPassword(params)
                Result.success(result)
            }catch (e:Failure){
                Result.error(e)
            }
        }
    }

    override suspend fun checkMobile(params: CheckMobileParams): Result<String, Failure> {
        return withContext(Dispatchers.IO) {
            try {
                val result = authRemoteDataSource.checkMobile(params)
                Result.success(result)
            }catch (e:Failure){
                Result.error(e)
            }
        }
    }

    override suspend fun checkVerificationCode(params: CheckVerificationCodeParams): Result<String, Failure> {
        return withContext(Dispatchers.IO) {
            try {
                val result = authRemoteDataSource.checkVerificationCode(params)
                authLocalDataSource.setToken(result)
                Result.success(result)
            }catch (e:Failure){
                Result.error(e)
            }
        }
    }

    override suspend fun getConfigs(params: GetConfigsParams): Result<Configs, Failure> {
        return withContext(Dispatchers.IO) {
            try {
                val result = authRemoteDataSource.getConfigs(params)
                Result.success(result)
            } catch (e:Failure){
                Result.error(e)
            }
        }
    }

}