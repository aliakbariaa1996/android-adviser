package ir.org.tavanesh.data.datasource

import ir.org.tavanesh.core.domain.question.entity.Question
import ir.org.tavanesh.core.domain.question.repository.AnswerQuestionParams
import ir.org.tavanesh.core.domain.question.repository.GetQuestionListParams
import ir.org.tavanesh.core.domain.question.repository.GetQuestionParams

interface QuestionRemoteDataSource {
    suspend fun answerQuestion(params: AnswerQuestionParams): Boolean

    suspend fun getQuestion(params: GetQuestionParams): Question

    suspend fun getQuestionList(params: GetQuestionListParams): List<Question>
}