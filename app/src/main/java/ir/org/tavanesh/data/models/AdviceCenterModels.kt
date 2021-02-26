package ir.org.tavanesh.data.models

import com.google.gson.annotations.SerializedName

data class AdviceCentersResponse(
    @field:SerializedName("current_page") var current_page:Int,
    @field:SerializedName("from") var from: Int,
    @field:SerializedName("items") var items: List<AdviceCenterModel>,
)
data class AdviceCenterModel(
    @field:SerializedName("id") var id: String,
    @field:SerializedName("name") var name: String,
    @field:SerializedName("description") var description: String,
    @field:SerializedName("logo") var logo: String,
    @field:SerializedName("color") var color:String,
    @field:SerializedName("address") var address:String,
    @field:SerializedName("latitude") var latitude:Double,
    @field:SerializedName("longitude") var longitude:Double,
    @field:SerializedName("phoneNumbers") var phoneNumbers:String,
    @field:SerializedName("advisorCount") var advisorCount:Int,
)
