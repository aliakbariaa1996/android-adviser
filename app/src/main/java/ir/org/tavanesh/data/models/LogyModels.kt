package ir.org.tavanesh.data.models

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName

@Entity(tableName = "tbl_logy")
data class LogyEntity(
    @PrimaryKey(autoGenerate = true) val id: Int=0,
    var type: String,
    var date: String,
    var duration: Int,
)

data class LogySendRequest(
    @field:SerializedName("x") var x: String,
)
