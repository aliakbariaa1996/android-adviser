package ir.org.tavanesh.data.models

import com.google.gson.annotations.SerializedName


data class AdvisersResponse(
    @field:SerializedName("current_page") var current_page:Int,
    @field:SerializedName("from") var from: Int,
    @field:SerializedName("items") var items: List<AdviserModel>,
)
data class AdviserModel(
    @field:SerializedName("id") var id: String,
    @field:SerializedName("lastname") var lastName: String,
    @field:SerializedName("firstname") var firstName:String,
    @field:SerializedName("description") var description: String,
    @field:SerializedName("avatar") var avatar: String,
    @field:SerializedName("specialty") var specialty:String,
)


data class AdvisersFreeTimeModel(
    @field:SerializedName("id") var id: String,
    @field:SerializedName("weekday") var weekday: String,
    @field:SerializedName("timeFrom") var timeFrom: String,
    @field:SerializedName("timeTo") var timeTo: String,
)
