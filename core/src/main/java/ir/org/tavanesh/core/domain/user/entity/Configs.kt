package ir.org.tavanesh.core.domain.user.entity

data class Configs(
    val educations: List<Education>? = null,
    val genders: List<Gender>? = null,
    val appVersionInfo: AppVersionInfo? = null,
)

data class Education(
    val id: Int,
    val key: String,
    val name: String,
)
data class Gender(
    val id: Int,
    val key: String,
    val name: String,
)

data class AppVersionInfo(
    val versionID: Int,
    val versionName: String,
    val downloadLink: String,
)
