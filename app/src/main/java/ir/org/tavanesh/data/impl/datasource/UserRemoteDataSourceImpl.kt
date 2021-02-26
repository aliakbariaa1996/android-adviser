package ir.org.tavanesh.data.impl.datasource

import ir.org.tavanesh.core.domain.user.entity.User
import ir.org.tavanesh.core.domain.user.repository.GetUserParams
import ir.org.tavanesh.core.domain.user.repository.UpdateUserParams
import ir.org.tavanesh.core.utils.Failure
import ir.org.tavanesh.core.utils.ProviderFailure
import ir.org.tavanesh.core.utils.UnKnownFailure
import ir.org.tavanesh.core.utils.provider_internet
import ir.org.tavanesh.data.datasource.AuthLocalDataSource
import ir.org.tavanesh.data.datasource.UserRemoteDataSource
import ir.org.tavanesh.data.mappers.toUser
import ir.org.tavanesh.data.mappers.waitResponse
import ir.org.tavanesh.data.platform.datasource.ResourcesRepository
import ir.org.tavanesh.data.platform.datasource.UserApi
import javax.inject.Inject

class UserRemoteDataSourceImpl @Inject constructor(
    private val userApi: UserApi,
    private val resourcesRepository: ResourcesRepository,
    private val authLocalDataSource: AuthLocalDataSource,
): UserRemoteDataSource {

    override suspend fun getUser(params: GetUserParams): User {
        return if (resourcesRepository.isInternetConnected()) {
            try {
                val token = authLocalDataSource.getToken()
                userApi.getUser(
                    token
                ).waitResponse {
                    it.toUser()
                }
            } catch (e: Exception) {
                if (e is Failure) throw e
                else throw UnKnownFailure()
            }
        } else {
            throw ProviderFailure(provider_internet)
        }
    }

    override suspend fun updateUser(params: UpdateUserParams): Boolean {
        return if (resourcesRepository.isInternetConnected()) {
            try {
                val token = authLocalDataSource.getToken()
                userApi.updateUser(
                    token, params.username, params.email, params.age, params.avatar
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

}