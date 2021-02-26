package ir.org.tavanesh.data.impl.datasource

import ir.org.tavanesh.core.domain.logy.repository.SendLogysParams
import ir.org.tavanesh.core.utils.Failure
import ir.org.tavanesh.core.utils.ProviderFailure
import ir.org.tavanesh.core.utils.UnKnownFailure
import ir.org.tavanesh.core.utils.provider_internet
import ir.org.tavanesh.data.datasource.AuthLocalDataSource
import ir.org.tavanesh.data.datasource.LogyRemoteDataSource
import ir.org.tavanesh.data.mappers.waitResponse
import ir.org.tavanesh.data.platform.datasource.LogyApi
import ir.org.tavanesh.data.platform.datasource.ResourcesRepository
import javax.inject.Inject

class LogyRemoteDataSourceImpl @Inject constructor(
    private val logyApi: LogyApi,
    private val resourcesRepository: ResourcesRepository,
    private val authLocalDataSource: AuthLocalDataSource,
): LogyRemoteDataSource {
    override suspend fun sendLogys(params: SendLogysParams): Boolean {
        return if (resourcesRepository.isInternetConnected()) {
            try {
                val token = authLocalDataSource.getToken()
                logyApi.sendLogys(
                    token, params.x!!
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