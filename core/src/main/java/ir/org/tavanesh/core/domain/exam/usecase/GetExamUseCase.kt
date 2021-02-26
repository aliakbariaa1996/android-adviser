package ir.org.tavanesh.core.domain.exam.usecase

import com.github.kittinunf.result.Result
import ir.org.tavanesh.core.domain.exam.entity.Exam
import ir.org.tavanesh.core.domain.exam.repository.ExamRepository
import ir.org.tavanesh.core.domain.exam.repository.GetExamParams
import ir.org.tavanesh.core.utils.Failure
import ir.org.tavanesh.core.utils.NoParamsFailure
import ir.org.tavanesh.core.utils.Usecase
import javax.inject.Inject

class GetExamUseCase @Inject constructor(
    private val examRepository: ExamRepository
) : Usecase<Exam, GetExamParams>() {
    override suspend fun call(
        params: GetExamParams?
    ): Result<Exam, Failure> {
        return params?.let {
            examRepository.getExam(params)
        } ?: run { Result.error(NoParamsFailure()) }
    }
}
