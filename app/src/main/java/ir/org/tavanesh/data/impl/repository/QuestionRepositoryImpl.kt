package ir.org.tavanesh.data.impl.repository

import com.github.kittinunf.result.Result
import ir.org.tavanesh.core.domain.question.entity.Question
import ir.org.tavanesh.core.domain.question.repository.AnswerQuestionParams
import ir.org.tavanesh.core.domain.question.repository.GetQuestionListParams
import ir.org.tavanesh.core.domain.question.repository.GetQuestionParams
import ir.org.tavanesh.core.domain.question.repository.QuestionRepository
import ir.org.tavanesh.core.utils.Failure
import ir.org.tavanesh.data.datasource.QuestionRemoteDataSource
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import javax.inject.Inject

class QuestionRepositoryImpl @Inject constructor(
    private val questionRemoteDataSource: QuestionRemoteDataSource,
): QuestionRepository {
    override suspend fun answerQuestion(params: AnswerQuestionParams): Result<Boolean, Failure> {
        return withContext(Dispatchers.IO) {
            try {
                val result = questionRemoteDataSource.answerQuestion(params)
                Result.success(result)
            } catch (e:Failure){
                Result.error(e)
            }
        }
    }

    override suspend fun getQuestion(params: GetQuestionParams): Result<Question, Failure> {
        return withContext(Dispatchers.IO) {
            try {
                val result = questionRemoteDataSource.getQuestion(params)
                Result.success(result)
            } catch (e:Failure){
                Result.error(e)
            }
        }
    }

    override suspend fun getQuestionList(params: GetQuestionListParams): Result<List<Question>, Failure> {
        return withContext(Dispatchers.IO) {
            try {
                val result = questionRemoteDataSource.getQuestionList(params)
                Result.success(result)
            } catch (e:Failure){
                Result.error(e)
            }
        }
    }

}