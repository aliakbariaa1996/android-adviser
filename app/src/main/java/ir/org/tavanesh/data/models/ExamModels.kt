package ir.org.tavanesh.data.models

import com.google.gson.annotations.SerializedName


data class ExamsResponse(
    @field:SerializedName("current_page") var current_page:Int,
    @field:SerializedName("from") var from: Int,
    @field:SerializedName("items") var items: List<ExamModel>,
)
data class ExamModel(
    @field:SerializedName("centerId") var centerId:String,
    @field:SerializedName("description") var description: String,
    @field:SerializedName("id") var id: String,
    @field:SerializedName("image") var image: String,
    @field:SerializedName("name") var name: String,
    @field:SerializedName("questionCount") var questionCount: Int,
    @field:SerializedName("testTime") var testTime: Int,
)

data class ExamAnswerRequest(
    @field:SerializedName("examId") var examId:String,
    @field:SerializedName("answers") var answers: String,
)

data class FeedbackResponse(
    @field:SerializedName("feedback") var feedback: String,
    @field:SerializedName("id") var id: String,
)