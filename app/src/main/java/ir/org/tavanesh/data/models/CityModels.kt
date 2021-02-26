package ir.org.tavanesh.data.models

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName

@Entity(tableName = "tbl_city")
data class CityEntity(
    @PrimaryKey(autoGenerate = true) val id: Int=0,
    var cityId: Int,
    var parentId: Int,
    var name: String,
)

data class CityProvinceResponse(
    @field:SerializedName("items") var items: List<ProvinceResponse>,
)

data class ProvinceResponse(
    @field:SerializedName("id") var id: Int,
    @field:SerializedName("name") var name: String,
    @field:SerializedName("cites") var cities: List<CityResponse>,
)

data class CityResponse(
    @field:SerializedName("id") var id: Int,
    @field:SerializedName("provinceId") var parentId: Int,
    @field:SerializedName("name") var name: String,
)