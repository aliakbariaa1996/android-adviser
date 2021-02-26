package ir.org.tavanesh.core.domain.question.usecase

import com.github.kittinunf.result.Result
import ir.org.tavanesh.core.domain.question.entity.Question
import ir.org.tavanesh.core.domain.question.repository.GetQuestionListParams
import ir.org.tavanesh.core.domain.question.repository.QuestionRepository
import ir.org.tavanesh.core.utils.Failure
import ir.org.tavanesh.core.utils.NoParamsFailure
import ir.org.tavanesh.core.utils.Usecase
import javax.inject.Inject

class GetQuestionListUseCase @Inject constructor(
    private val questionRepository: QuestionRepository
) : Usecase<List<Question>, GetQuestionListParams>() {
    override suspend fun call(
        params: GetQuestionListParams?
    ): Result<List<Question>, Failure> {
        return params?.let {
            questionRepository.getQuestionList(params)
        } ?: run { Result.error(NoParamsFailure()) }
    }
}
