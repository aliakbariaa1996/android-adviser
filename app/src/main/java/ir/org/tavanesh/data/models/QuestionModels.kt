package ir.org.tavanesh.data.models

import com.google.gson.annotations.SerializedName

data class ExamQuestionsResponse(
    @field:SerializedName("items") var items: List<QuestionModel>,
)

data class QuestionModel(
    @field:SerializedName("id") var id: String,
    @field:SerializedName("title") var title: String,
    @field:SerializedName("type") var type: String,
    @field:SerializedName("isRequired") var isRequired: Boolean,
    @field:SerializedName("answers") var answers: List<AnswerModel>?=null,
    @field:SerializedName("timeView") var timeView: Int?=null,
)

data class AnswerModel(
    @field:SerializedName("id") var id: String,
    @field:SerializedName("title") var title: String,
    @field:SerializedName("questionId") var questionId: String,
)

