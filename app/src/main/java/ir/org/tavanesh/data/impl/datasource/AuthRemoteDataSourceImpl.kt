package ir.org.tavanesh.data.impl.datasource

import ir.org.tavanesh.core.domain.user.entity.Configs
import ir.org.tavanesh.core.domain.user.entity.User
import ir.org.tavanesh.core.domain.user.repository.*
import ir.org.tavanesh.core.utils.Failure
import ir.org.tavanesh.core.utils.ProviderFailure
import ir.org.tavanesh.core.utils.UnKnownFailure
import ir.org.tavanesh.core.utils.provider_internet
import ir.org.tavanesh.data.datasource.AuthLocalDataSource
import ir.org.tavanesh.data.datasource.AuthRemoteDataSource
import ir.org.tavanesh.data.mappers.toConfigs
import ir.org.tavanesh.data.mappers.toUser
import ir.org.tavanesh.data.mappers.waitResponse
import ir.org.tavanesh.data.platform.datasource.AuthApi
import ir.org.tavanesh.data.platform.datasource.ResourcesRepository
import javax.inject.Inject

class AuthRemoteDataSourceImpl @Inject constructor(
    private val authApi: AuthApi,
    private val resourcesRepository: ResourcesRepository,
    private val authLocalDataSource: AuthLocalDataSource,
) : AuthRemoteDataSource {

    override suspend fun forgetPassword(params: ForgetPasswordParams): String {
        return if (resourcesRepository.isInternetConnected()) {
            try {
                authApi.forgetPassword(
                    params.mobile
                ).waitResponse {
                    it.secretKey
                }
            } catch (e: Exception) {
                if (e is Failure) throw e
                else throw UnKnownFailure()
            }
        } else {
            throw ProviderFailure(provider_internet)
        }
    }

    override suspend fun loginUser(params: LoginUserParams): String {
        return if (resourcesRepository.isInternetConnected()) {
            try {
                authApi.login(
                    params.username,params.password
                ).waitResponse {
                    it.accessToken
                }
            } catch (e: Exception) {
                if (e is Failure) throw e
                else throw UnKnownFailure()
            }
        } else {
            throw ProviderFailure(provider_internet)
        }
    }

    override suspend fun registerUser(params: RegisterUserParams): User {
        return if (resourcesRepository.isInternetConnected()) {
            try {
                val token = authLocalDataSource.getToken()
                authApi.register(
                    token,
                    age = params.age!!,
                    gender = params.gender!!,
                    username = params.username!!,
                    nationalCode = params.nationalCode!!,
                    firstname = params.firstName!!,
                    lastname = params.lastName!!,
                    password = params.password!!,
                    email = params.email!!,
                    education = params.education!!,
                ).waitResponse { response ->
                    response.toUser()
                }
            } catch (e: Exception) {
                if (e is Failure) throw e
                else throw UnKnownFailure()
            }
        } else {
            throw ProviderFailure(provider_internet)
        }
    }

    override suspend fun logoutUser(params: LogoutUserParams): Boolean {
        return if (resourcesRepository.isInternetConnected()) {
            try {
                val token = authLocalDataSource.getToken()
                authApi.logout(token).waitResponse {
                    true
                }
            } catch (e: Exception) {
                if (e is Failure) throw e
                else throw UnKnownFailure()
            }
        } else {
            throw ProviderFailure(provider_internet)
        }
    }

    override suspend fun setPassword(params: SetPasswordParams): Boolean {
        return if (resourcesRepository.isInternetConnected()) {
            try {
                val token = authLocalDataSource.getToken()
                authApi.changePassword(
                    token,
                        password = params.password,
                ).waitResponse {
                    true
                }
            } catch (e: Exception) {
                if (e is Failure) throw e
                else throw UnKnownFailure()
            }
        } else {
            throw ProviderFailure(provider_internet)
        }
    }

    override suspend fun checkMobile(params: CheckMobileParams): String {
        return if (resourcesRepository.isInternetConnected()) {
            try {
                authApi.checkMobileNumber(
                    params.mobile
                ).waitResponse {
                    it.secretKey
                }
            } catch (e: Exception) {
                if (e is Failure) throw e
                else throw UnKnownFailure()
            }
        } else {
            throw ProviderFailure(provider_internet)
        }
    }

    override suspend fun checkVerificationCode(params: CheckVerificationCodeParams): String {
        return if (resourcesRepository.isInternetConnected()) {
            try {
                authApi.checkVerificationCode(
                    params.verifyCode,
                    params.secretKey,
                ).waitResponse {
                    it.accessToken
                }
            } catch (e: Exception) {
                if (e is Failure) throw e
                else throw UnKnownFailure()
            }
        } else {
            throw ProviderFailure(provider_internet)
        }
    }

    override suspend fun getConfigs(params: GetConfigsParams): Configs {
        return if (resourcesRepository.isInternetConnected()) {
            try {
                authApi.getConfigs().waitResponse {
                    it.toConfigs()
                }
            } catch (e: Exception) {
                if (e is Failure) throw e
                else throw UnKnownFailure()
            }
        } else {
            throw ProviderFailure(provider_internet)
        }
    }


}