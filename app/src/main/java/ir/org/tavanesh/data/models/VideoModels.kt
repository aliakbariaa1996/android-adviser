package ir.org.tavanesh.data.models

import com.google.gson.annotations.SerializedName

data class VideoModel(
    @field:SerializedName("id") var id: String,
    @field:SerializedName("courseId") var courseId: String,
    @field:SerializedName("title") var name: String,
    @field:SerializedName("description") var description: String,
    @field:SerializedName("link") var videoUrl: String,
    @field:SerializedName("questions") var questions: List<QuestionModel>?=null,
)