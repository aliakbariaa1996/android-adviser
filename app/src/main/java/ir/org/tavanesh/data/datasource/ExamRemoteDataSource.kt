package ir.org.tavanesh.data.datasource

import ir.org.tavanesh.core.domain.exam.entity.Exam
import ir.org.tavanesh.core.domain.exam.repository.AnswerExamParams
import ir.org.tavanesh.core.domain.exam.repository.GetExamListParams
import ir.org.tavanesh.core.domain.exam.repository.GetExamParams
import ir.org.tavanesh.data.models.ExamAnswerRequest

interface ExamRemoteDataSource {
    suspend fun getExamList(params: GetExamListParams): List<Exam>

    suspend fun getExam(params: GetExamParams): Exam

    suspend fun answerExam(params: ExamAnswerRequest): String
}