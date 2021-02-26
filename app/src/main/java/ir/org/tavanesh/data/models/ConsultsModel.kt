package ir.org.tavanesh.data.models

import com.google.gson.annotations.SerializedName

data class ConsultsResponse(
    @field:SerializedName("current_page") var current_page:Int,
    @field:SerializedName("from") var from: Int,
    @field:SerializedName("items") var items: List<ConsultModel>,
)

data class SubmitConsultModel(
    @field:SerializedName("advisorId") var advisorId: String,
    @field:SerializedName("advisorWeekdayTimeId") var advisorWeekdayTimeId: String,
    @field:SerializedName("centerId") var centerId :String,
    @field:SerializedName("date") var date: String,
    @field:SerializedName("id") var id: String,
    @field:SerializedName("status") var status: String,
    @field:SerializedName("userId") var userId: String,
)

data class ConsultModel(
    @field:SerializedName("advisor") var advisor: AdviserModel,
    @field:SerializedName("center") var center: AdviceCenterModel,
    @field:SerializedName("date") var date: String,
    @field:SerializedName("id") var id: String,
    @field:SerializedName("status") var status: String,
    @field:SerializedName("weekday") var weekday: AdvisersFreeTimeModel,
)
