package ir.org.tavanesh.core.domain.user.entity

import com.google.gson.annotations.SerializedName
import java.io.Serializable

enum class UserStatus(val status: String) : Serializable {
    @SerializedName("REGISTER")
    REGISTER("REGISTER"),

    @SerializedName("QUESTIONNAIRE")
    QUESTIONNAIRE("QUESTIONNAIRE"),

    @SerializedName("ACTIVE")
    ACTIVE("ACTIVE"),

    @SerializedName("BAN")
    BAN("BAN"),
}

fun String.toUserStatus(): UserStatus {
    return when (this) {
        UserStatus.REGISTER.status -> UserStatus.REGISTER
        UserStatus.QUESTIONNAIRE.status -> UserStatus.QUESTIONNAIRE
        UserStatus.ACTIVE.status -> UserStatus.ACTIVE
        UserStatus.BAN.status -> UserStatus.BAN
        else -> UserStatus.BAN
    }
}