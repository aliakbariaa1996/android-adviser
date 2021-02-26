package ir.org.tavanesh.data.impl.datasource

import ir.org.tavanesh.core.domain.advisor.entity.AdviceCenter
import ir.org.tavanesh.core.domain.advisor.entity.Adviser
import ir.org.tavanesh.core.domain.advisor.entity.AdviserTime
import ir.org.tavanesh.core.domain.advisor.repository.*
import ir.org.tavanesh.core.utils.Failure
import ir.org.tavanesh.core.utils.ProviderFailure
import ir.org.tavanesh.core.utils.UnKnownFailure
import ir.org.tavanesh.core.utils.provider_internet
import ir.org.tavanesh.data.datasource.AdviserRemoteDataSource
import ir.org.tavanesh.data.datasource.AuthLocalDataSource
import ir.org.tavanesh.data.mappers.toAdviceCenter
import ir.org.tavanesh.data.mappers.toAdviser
import ir.org.tavanesh.data.mappers.toAdviserFreeTime
import ir.org.tavanesh.data.mappers.waitResponse
import ir.org.tavanesh.data.platform.datasource.AdviserApi
import ir.org.tavanesh.data.platform.datasource.ResourcesRepository
import javax.inject.Inject

class AdviserRemoteDataSourceImpl @Inject constructor(
    private val adviserApi: AdviserApi,
    private val resourcesRepository: ResourcesRepository,
    private val authLocalDataSource: AuthLocalDataSource,
) : AdviserRemoteDataSource {
    override suspend fun getAdviserFreeTimesByDate(params: GetAdviserFreeTimesByDataParams): List<AdviserTime> {
        return if (resourcesRepository.isInternetConnected()) {
            try {
                val token = authLocalDataSource.getToken()
                adviserApi.getAdviserFreeTimes(
                    token, params.adviserId, params.date
                ).waitResponse {
                    it.toAdviserFreeTime()
                }
            } catch (e: Exception) {
                if (e is Failure) throw e
                else throw UnKnownFailure()
            }
        } else {
            throw ProviderFailure(provider_internet)
        }
    }

    override suspend fun getAdviser(params: GetAdviserParams): Adviser {
        return if (resourcesRepository.isInternetConnected()) {
            try {
                val token = authLocalDataSource.getToken()
                adviserApi.getAdviser(
                    token, params.adviserId
                ).waitResponse {
                    it.toAdviser()
                }
            } catch (e: Exception) {
                if (e is Failure) throw e
                else throw UnKnownFailure()
            }
        } else {
            throw ProviderFailure(provider_internet)
        }
    }

    override suspend fun getAdvisers(params: GetAdvisersParams): List<Adviser> {
        return if (resourcesRepository.isInternetConnected()) {
            try {
                val token = authLocalDataSource.getToken()
                adviserApi.getAdvisers(
                    token, params.page, params.adviceCenterId
                ).waitResponse {
                    it.items.toAdviser()
                }
            } catch (e: Exception) {
                if (e is Failure) throw e
                else throw UnKnownFailure()
            }
        } else {
            throw ProviderFailure(provider_internet)
        }
    }

    override suspend fun getAdviceCenters(params: GetAdviceCentersParams): List<AdviceCenter> {
        return if (resourcesRepository.isInternetConnected()) {
            try {
                val token = authLocalDataSource.getToken()
                adviserApi.getAdviceCenters(
                    token, params.page, params.provinceId, params.cityId
                ).waitResponse {
                    it.items.toAdviceCenter()
                }
            } catch (e: Exception) {
                if (e is Failure) throw e
                else throw UnKnownFailure()
            }
        } else {
            throw ProviderFailure(provider_internet)
        }
    }

    override suspend fun getAdviceCenter(params: GetAdviceCenterParams): AdviceCenter {
        return if (resourcesRepository.isInternetConnected()) {
            try {
                val token = authLocalDataSource.getToken()
                adviserApi.getAdviceCenter(
                    token, params.adviceCenterId
                ).waitResponse {
                    it.toAdviceCenter()
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