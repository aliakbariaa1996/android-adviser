package ir.org.tavanesh.core.domain.exam.usecase

import com.github.kittinunf.result.Result
import ir.org.tavanesh.core.domain.exam.entity.Exam
import ir.org.tavanesh.core.domain.exam.repository.ExamRepository
import ir.org.tavanesh.core.domain.exam.repository.GetExamListParams
import ir.org.tavanesh.core.utils.Failure
import ir.org.tavanesh.core.utils.NoParamsFailure
import ir.org.tavanesh.core.utils.Usecase
import javax.inject.Inject

class GetExamListUseCase @Inject constructor(
    private val examRepository: ExamRepository
) : Usecase<List<Exam>, GetExamListParams>() {
    override suspend fun call(
        params: GetExamListParams?
    ): Result<List<Exam>, Failure> {
        return params?.let {
            examRepository.getExamList(params)
        } ?: run { Result.error(NoParamsFailure()) }
    }
}
