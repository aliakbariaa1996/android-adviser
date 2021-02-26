package ir.org.tavanesh.core.domain.exam.repository

import com.github.kittinunf.result.Result
import ir.org.tavanesh.core.domain.exam.entity.Exam
import ir.org.tavanesh.core.utils.Failure

interface ExamRepository {
    suspend fun getExamList(params: GetExamListParams): Result<List<Exam>, Failure>

    suspend fun getExam(params: GetExamParams): Result<Exam, Failure>

    suspend fun answerExam(params: AnswerExamParams): Result<String, Failure>
}