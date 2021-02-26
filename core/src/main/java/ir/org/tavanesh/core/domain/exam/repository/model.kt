package ir.org.tavanesh.core.domain.exam.repository

import ir.org.tavanesh.core.domain.question.entity.Question

data class GetExamParams(
    var examId: String,
)

data class GetExamListParams(
    var page: Int,
)

data class AnswerExamParams(
    var examId: String,
    var questions: List<Question>,
)