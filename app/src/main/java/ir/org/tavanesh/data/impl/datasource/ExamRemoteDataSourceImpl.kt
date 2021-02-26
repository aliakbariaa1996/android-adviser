package ir.org.tavanesh.data.impl.datasource

import ir.org.tavanesh.core.domain.exam.entity.Exam
import ir.org.tavanesh.core.domain.exam.repository.AnswerExamParams
import ir.org.tavanesh.core.domain.exam.repository.GetExamListParams
import ir.org.tavanesh.core.domain.exam.repository.GetExamParams
import ir.org.tavanesh.core.utils.Failure
import ir.org.tavanesh.core.utils.ProviderFailure
import ir.org.tavanesh.core.utils.UnKnownFailure
import ir.org.tavanesh.core.utils.provider_internet
import ir.org.tavanesh.data.datasource.AuthLocalDataSource
import ir.org.tavanesh.data.datasource.ExamRemoteDataSource
import ir.org.tavanesh.data.mappers.toExam
import ir.org.tavanesh.data.mappers.waitResponse
import ir.org.tavanesh.data.models.ExamAnswerRequest
import ir.org.tavanesh.data.platform.datasource.ExamApi
import ir.org.tavanesh.data.platform.datasource.ResourcesRepository
import timber.log.Timber
import javax.inject.Inject

class ExamRemoteDataSourceImpl @Inject constructor(
    private val examApi: ExamApi,
    private val resourcesRepository: ResourcesRepository,
    private val authLocalDataSource: AuthLocalDataSource,
) : ExamRemoteDataSource {
    override suspend fun getExamList(params: GetExamListParams): List<Exam> {
        return if (resourcesRepository.isInternetConnected()) {
            try {
                val token = authLocalDataSource.getToken()
                examApi.getExams(
                    token, params.page
                ).waitResponse {
                    it.items.toExam()
                }
            } catch (e: Exception) {
                if (e is Failure) throw e
                else throw UnKnownFailure()
            }
        } else {
            throw ProviderFailure(provider_internet)
        }
    }

    override suspend fun getExam(params: GetExamParams): Exam {
        return if (resourcesRepository.isInternetConnected()) {
            try {
                val token = authLocalDataSource.getToken()
                examApi.getExam(
                    token, params.examId
                ).waitResponse {
                    it.toExam()
                }
            } catch (e: Exception) {
                if (e is Failure) throw e
                else throw UnKnownFailure()
            }
        } else {
            throw ProviderFailure(provider_internet)
        }
    }

    override suspend fun answerExam(params:ExamAnswerRequest): String {
        return if (resourcesRepository.isInternetConnected()) {
            try {
                val token = authLocalDataSource.getToken()
                Timber.tag("TAG").d("answer of ${params.examId}: ${params.answers}")
                examApi.setExamAnswer(
                    token, params.examId, params.answers
                ).waitResponse {
                    it.feedback
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