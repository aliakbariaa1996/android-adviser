package ir.org.tavanesh.data.platform.model

import com.google.gson.annotations.SerializedName
import java.io.Serializable


enum class ForgetPasswordViews(val status: String) : Serializable {
    @SerializedName("MOBILE")
    MOBILE("MOBILE"),

    @SerializedName("VERIFY_CODE")
    VERIFY_CODE("VERIFY_CODE"),

    @SerializedName("PASSWORD")
    PASSWORD("PASSWORD"),
}