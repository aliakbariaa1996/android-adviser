package ir.org.tavanesh.data.impl.datasource

import ir.org.tavanesh.core.domain.consult.entity.Consult
import ir.org.tavanesh.core.domain.consult.repository.GetConsultingInfoParams
import ir.org.tavanesh.core.domain.consult.repository.GetConsultingHistoryParams
import ir.org.tavanesh.core.domain.consult.repository.SubmitConsultingParams
import ir.org.tavanesh.core.utils.Failure
import ir.org.tavanesh.core.utils.ProviderFailure
import ir.org.tavanesh.core.utils.UnKnownFailure
import ir.org.tavanesh.core.utils.provider_internet
import ir.org.tavanesh.data.datasource.AuthLocalDataSource
import ir.org.tavanesh.data.datasource.ConsultRemoteDataSource
import ir.org.tavanesh.data.mappers.toConsult
import ir.org.tavanesh.data.mappers.waitResponse
import ir.org.tavanesh.data.platform.datasource.ConsultApi
import ir.org.tavanesh.data.platform.datasource.ResourcesRepository
import javax.inject.Inject

class ConsultRemoteDataSourceImpl @Inject constructor(
    private val consultApi: ConsultApi,
    private val resourcesRepository: ResourcesRepository,
    private val authLocalDataSource: AuthLocalDataSource,
) : ConsultRemoteDataSource {
    override suspend fun getConsultingInfo(params: GetConsultingInfoParams): Consult {
        return if (resourcesRepository.isInternetConnected()) {
            try {
                val token = authLocalDataSource.getToken()
                consultApi.getConsultation(
                    token, params.consultId
                ).waitResponse {
                    it.toConsult()
                }
            } catch (e: Exception) {
                if (e is Failure) throw e
                else throw UnKnownFailure()
            }
        } else {
            throw ProviderFailure(provider_internet)
        }
    }

    override suspend fun getConsultingHistory(params: GetConsultingHistoryParams): List<Consult> {
        return if (resourcesRepository.isInternetConnected()) {
            try {
                val token = authLocalDataSource.getToken()
                consultApi.getConsultations(
                    token, params.page
                ).waitResponse {
                    it.items.toConsult()
                }
            } catch (e: Exception) {
                if (e is Failure) throw e
                else throw UnKnownFailure()
            }
        } else {
            throw ProviderFailure(provider_internet)
        }
    }

    override suspend fun submitConsult(params: SubmitConsultingParams): Consult {
        return if (resourcesRepository.isInternetConnected()) {
            try {
                val token = authLocalDataSource.getToken()
                consultApi.createConsultation(
                    token, params.adviserId, params.date, params.timeId, params.centerId

                ).waitResponse {
                    it.toConsult()
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