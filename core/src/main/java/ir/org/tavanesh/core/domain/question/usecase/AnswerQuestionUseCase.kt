package ir.org.tavanesh.core.domain.question.usecase

import com.github.kittinunf.result.Result
import ir.org.tavanesh.core.domain.question.repository.AnswerQuestionParams
import ir.org.tavanesh.core.domain.question.repository.QuestionRepository
import ir.org.tavanesh.core.utils.Failure
import ir.org.tavanesh.core.utils.NoParamsFailure
import ir.org.tavanesh.core.utils.Usecase
import javax.inject.Inject

class AnswerQuestionUseCase @Inject constructor(
    private val questionRepository: QuestionRepository
) : Usecase<Boolean, AnswerQuestionParams>() {
    override suspend fun call(
        params: AnswerQuestionParams?
    ): Result<Boolean, Failure> {
        return params?.let {
            questionRepository.answerQuestion(params)
        } ?: run { Result.error(NoParamsFailure()) }
    }
}
