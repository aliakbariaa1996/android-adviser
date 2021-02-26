package ir.org.tavanesh.core.domain.exam.usecase

import com.github.kittinunf.result.Result
import ir.org.tavanesh.core.domain.exam.repository.AnswerExamParams
import ir.org.tavanesh.core.domain.exam.repository.ExamRepository
import ir.org.tavanesh.core.utils.Failure
import ir.org.tavanesh.core.utils.NoParamsFailure
import ir.org.tavanesh.core.utils.Usecase
import javax.inject.Inject

class AnswerExamUseCase @Inject constructor(
    private val examRepository: ExamRepository
) : Usecase<String, AnswerExamParams>() {
    override suspend fun call(
        params: AnswerExamParams?
    ): Result<String, Failure> {
        return params?.let {
            examRepository.answerExam(params)
        } ?: run { Result.error(NoParamsFailure()) }
    }
}
