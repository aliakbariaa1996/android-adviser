package ir.org.tavanesh.data.platform.model

import com.google.gson.annotations.SerializedName
import java.io.Serializable


enum class AdviserViews(val status: String) : Serializable {
    @SerializedName("PROFILE")
    PROFILE("PROFILE"),

    @SerializedName("TIME_PICK")
    TIME_PICK("TIME_PICK"),

    @SerializedName("DONE")
    DONE("DONE"),
}