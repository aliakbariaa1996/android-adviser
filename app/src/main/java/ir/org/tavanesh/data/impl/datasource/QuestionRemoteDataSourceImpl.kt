package ir.org.tavanesh.data.impl.datasource

import ir.org.tavanesh.core.domain.question.entity.Question
import ir.org.tavanesh.core.domain.question.repository.AnswerQuestionParams
import ir.org.tavanesh.core.domain.question.repository.GetQuestionListParams
import ir.org.tavanesh.core.domain.question.repository.GetQuestionParams
import ir.org.tavanesh.core.utils.Failure
import ir.org.tavanesh.core.utils.ProviderFailure
import ir.org.tavanesh.core.utils.UnKnownFailure
import ir.org.tavanesh.core.utils.provider_internet
import ir.org.tavanesh.data.datasource.AuthLocalDataSource
import ir.org.tavanesh.data.datasource.QuestionRemoteDataSource
import ir.org.tavanesh.data.mappers.toQuestion
import ir.org.tavanesh.data.mappers.waitResponse
import ir.org.tavanesh.data.platform.datasource.QuestionApi
import ir.org.tavanesh.data.platform.datasource.ResourcesRepository
import timber.log.Timber
import javax.inject.Inject

class QuestionRemoteDataSourceImpl @Inject constructor(
    private val questionApi: QuestionApi,
    private val resourcesRepository: ResourcesRepository,
    private val authLocalDataSource: AuthLocalDataSource,
) : QuestionRemoteDataSource {
    override suspend fun answerQuestion(params: AnswerQuestionParams): Boolean {
        return if (resourcesRepository.isInternetConnected()) {
            try {
                val token = authLocalDataSource.getToken()
                Timber.tag("TAG").d("answer of ${params.episodeId}: ${params.answers}")
                questionApi.answerQuestion(
                    token, params.episodeId, params.answers
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

    override suspend fun getQuestion(params: GetQuestionParams): Question {
        TODO("Not yet implemented")
    }

    override suspend fun getQuestionList(params: GetQuestionListParams): List<Question> {
        return if (resourcesRepository.isInternetConnected()) {
            try {
                val token = authLocalDataSource.getToken()
                questionApi.getExamQuestions(
                    token, params.objectId
                ).waitResponse {
                    it.items.toQuestion()
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