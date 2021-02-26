package ir.org.tavanesh.data.models

import com.google.gson.annotations.SerializedName

data class CourseResponse(
    @field:SerializedName("current_page") var current_page:Int,
    @field:SerializedName("from") var from: Int,
    @field:SerializedName("items") var items: List<CourseModel>,
)

data class CourseModel(
    @field:SerializedName("id") var id: String,
    @field:SerializedName("title") var title: String,
    @field:SerializedName("description") var description: String,
    @field:SerializedName("logo") var image: String,
    @field:SerializedName("lecturerName") var teacherName:String,
    @field:SerializedName("rate") var rate: Int,
    @field:SerializedName("color") var color: String,
    @field:SerializedName("duration") var duration: String,
    @field:SerializedName("episodes") var videos: List<VideoModel>?=null,
)






























