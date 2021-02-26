package ir.org.tavanesh.core.domain.question.repository

import com.github.kittinunf.result.Result
import ir.org.tavanesh.core.domain.question.entity.Question
import ir.org.tavanesh.core.utils.Failure

interface QuestionRepository {
    suspend fun answerQuestion(params: AnswerQuestionParams): Result<Boolean, Failure>

    suspend fun getQuestion(params: GetQuestionParams): Result<Question, Failure>

    suspend fun getQuestionList(params: GetQuestionListParams): Result<List<Question>, Failure>
}