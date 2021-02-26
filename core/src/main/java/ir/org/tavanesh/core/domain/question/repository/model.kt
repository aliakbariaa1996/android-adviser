package ir.org.tavanesh.core.domain.question.repository

data class AnswerQuestionParams(
    var episodeId: String,
    var answers: String,
)

data class GetQuestionParams(
    var questionId: String? = null,
)

data class GetQuestionListParams(
    var objectId: String,
)