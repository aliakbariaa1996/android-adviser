package ir.org.tavanesh.core.domain.question.usecase

import com.github.kittinunf.result.Result
import ir.org.tavanesh.core.domain.question.entity.Question
import ir.org.tavanesh.core.domain.question.repository.GetQuestionParams
import ir.org.tavanesh.core.domain.question.repository.QuestionRepository
import ir.org.tavanesh.core.utils.Failure
import ir.org.tavanesh.core.utils.NoParamsFailure
import ir.org.tavanesh.core.utils.Usecase
import javax.inject.Inject

class GetQuestionUseCase @Inject constructor(
    private val questionRepository: QuestionRepository
) : Usecase<Question, GetQuestionParams>() {
    override suspend fun call(
        params: GetQuestionParams?
    ): Result<Question, Failure> {
        return params?.let {
            questionRepository.getQuestion(params)
        } ?: run { Result.error(NoParamsFailure()) }
    }
}
